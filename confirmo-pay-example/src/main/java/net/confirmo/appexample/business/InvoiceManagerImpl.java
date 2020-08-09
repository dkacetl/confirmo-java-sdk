package net.confirmo.appexample.business;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.Currency;
import net.confirmo.api.model.InvoiceDetailResponse;
import net.confirmo.api.query.BitcoinPayStatus;
import net.confirmo.api.tools.InvoiceRequestBuilder;
import net.confirmo.appexample.ConfirmoPayExampleProperties;
import net.confirmo.appexample.db.InvoiceEntity;
import net.confirmo.appexample.db.InvoiceRepository;
import net.confirmo.appexample.db.InvoiceStatus;
import net.confirmo.appexample.model.Invoice;
import net.confirmo.spring.invoice.InvoiceException;
import net.confirmo.spring.invoice.InvoiceNotFoundException;
import net.confirmo.spring.invoice.InvoiceService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
public class InvoiceManagerImpl implements InvoiceManager {

    private InvoiceService invoiceService;

    private ConfirmoPayExampleProperties confirmoPayExampleProperties;

    private InvoiceRequestBuilderFactory invoiceRequestBuilderFactory;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceManagerImpl(InvoiceService invoiceService, ConfirmoPayExampleProperties confirmoPayExampleProperties, InvoiceRequestBuilderFactory invoiceRequestBuilderFactory, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.confirmoPayExampleProperties = confirmoPayExampleProperties;
        this.invoiceRequestBuilderFactory = invoiceRequestBuilderFactory;
        this.invoiceRepository = invoiceRepository;
    }

    public String generateInvoiceId() {
        return UUID.randomUUID().toString();
    }

    public Invoice handleInvoiceCallback(String id, BitcoinPayStatus bitcoinPayStatus) {

        // todo: validate access to id
        // get all data for invoice, also from confirmo API
        // it is secure
        Invoice invoice = loadInvoice(id);

        if (invoice.getInvoiceDetailResponse()==null) {
            throw new IllegalStateException("Invoice is not ready yet.");
        }

        return synchronize(invoice);
    }

    /**
     * Full load invoice from db as well as from confirmo web.
     *
     * @param id id of invoice
     * @return invoice object
     */
    public Invoice loadInvoice(String id) throws InvoiceNotFoundException {
        // Load from db
        InvoiceEntity invoiceEntity = invoiceRepository
                .findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));
        String confirmoInvoiceId = invoiceEntity.getConfirmoInvoiceId();

        // Get invoice from confirmo, for security reason
        // TODO: handle all exceptions and states
        try {
            InvoiceDetailResponse response = invoiceService.getOne(confirmoInvoiceId);
            return new Invoice(invoiceEntity, response);
        } catch (RestClientException e) {
            throw new InvoiceException(e, id);
        }
    }

    /**
     * Source of truth for invoice is Confirmo.net invoice
     * TODO: sync all the properties, not only
     *
     * method synchronize invoiceEntity and confrimoInvoice data
     * @param invoice fully loaded invoice
     * @return invoice
     */
    @Transactional
    public Invoice synchronize(Invoice invoice) {
        if (invoice==null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }
        InvoiceEntity invoiceEntity = invoice.getInvoiceEntity();
        invoiceEntity.setStatus(invoice.getStatus());
        invoiceEntity.setConfirmoInvoiceId(invoice.getInvoiceDetailResponse().getId());
        invoiceRepository.saveAndFlush(invoiceEntity);
        return invoice;
    }

    /**
     *
     * @param amount
     * @param id
     * @return
     */
    public Invoice createInvoice(String id, float amount, Currency targetCryptocurrency) {

        InvoiceEntity invoiceEntity = createInvoiceEntity(id, amount, targetCryptocurrency);

        CreateNewInvoiceRequest invoiceRequest = createBuilder(id)
                .product("Confirmo product example","Pleas pay for me, "+id)
                .invoice(Currency.CZK, amount, targetCryptocurrency)
                .build();

        InvoiceDetailResponse invoiceDetailResponse = invoiceService.create(invoiceRequest);

        Invoice invoice = new Invoice(invoiceEntity, invoiceDetailResponse);
        synchronize(invoice);

        return new Invoice(invoiceEntity, invoiceDetailResponse);
    }

    @Transactional
    public InvoiceEntity createInvoiceEntity(String id, float amount, Currency currency) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(amount);
        invoiceEntity.setId(id);
        invoiceEntity.setStatus(InvoiceStatus.created);
        invoiceEntity.setCurrency(currency);
        return invoiceRepository.saveAndFlush(invoiceEntity);
    }

    private InvoiceRequestBuilder createBuilder(String reference) {
        String notifyUrl = null;
        if (!StringUtils.isEmpty(confirmoPayExampleProperties.getNotifyUrl())) {
            notifyUrl = confirmoPayExampleProperties.getNotifyUrl() + "/"+reference;
        }

        String returnUrl = null;
        if (!StringUtils.isEmpty(confirmoPayExampleProperties.getReturnUrl())) {
            returnUrl = confirmoPayExampleProperties.getReturnUrl() + "/"+reference;
        }

        return invoiceRequestBuilderFactory
                .create()
                .reference(reference, notifyUrl, returnUrl);
    }
}
