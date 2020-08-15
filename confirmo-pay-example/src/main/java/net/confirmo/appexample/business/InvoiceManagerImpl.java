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
import net.confirmo.appexample.model.PaginationData;
import net.confirmo.spring.invoice.InvoiceException;
import net.confirmo.spring.invoice.InvoiceNotFoundException;
import net.confirmo.spring.invoice.InvoiceService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<Invoice> getAll(PaginationData paginationData) {
        Pageable page = PageRequest.of(paginationData.getPage(),paginationData.getSize());
        return invoiceRepository.findAll(page).stream()
                .map( (entity) -> new Invoice(entity, null) )
                .collect(Collectors.toList());
    }

    /**
     * Full load invoice from db as well as from confirmo web.
     *
     * @param id id of invoice
     * @return invoice object
     */
    public Invoice fullLoadInvoice(String id) throws InvoiceNotFoundException {
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
     * TODO: sync all important properties, not only status
     *
     * method synchronize invoiceEntity and confrimoInvoice data
     * @param invoice fully loaded invoice
     * @return invoice
     */
    public Invoice synchronize(Invoice invoice) {
        if (invoice==null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }
        InvoiceEntity invoiceEntity = invoice.getInvoiceEntity();
        invoiceEntity.setStatus(invoice.getStatus());
        invoiceEntity.setConfirmoInvoiceId(invoice.getInvoiceDetailResponse().getId());
        invoiceRepository.save(invoiceEntity);
        return invoice;
    }

    /**
     *
     * @param amount
     * @param id
     * @return
     */
    @Override
    public Invoice createInvoice(String id, float amount, Currency targetCryptocurrency) {

        InvoiceEntity invoiceEntity = createInvoiceEntity(id, amount, targetCryptocurrency);

        CreateNewInvoiceRequest invoiceRequest =
                invoiceRequestBuilder(id)
                .product("Confirmo product example","Please pay for me, "+id)
                .invoiceAmount(amount)
                .invoiceCurrency(Currency.CZK, targetCryptocurrency)
                .build();

        InvoiceDetailResponse invoiceDetailResponse = invoiceService.create(invoiceRequest);

        Invoice invoice = new Invoice(invoiceEntity, invoiceDetailResponse);
        synchronize(invoice);

        return new Invoice(invoiceEntity, invoiceDetailResponse);
    }

    @Override
    public Invoice handleInvoice(String id) {

        // todo: validate access to id
        // get all data for invoice, also from confirmo API
        // it is secure
        Invoice invoice = fullLoadInvoice(id);

        if (invoice.getInvoiceDetailResponse()==null) {
            throw new IllegalStateException("Invoice is not ready yet.");
        }

        invoice = synchronize(invoice);

        return synchronize(invoice);
    }

    private InvoiceEntity createInvoiceEntity(String id, float amount, Currency currency) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(amount);
        invoiceEntity.setId(id);
        invoiceEntity.setStatus(InvoiceStatus.created);
        invoiceEntity.setCurrency(currency);
        invoiceEntity.setCreated(LocalDateTime.now());
        invoiceEntity.setUpdated(LocalDateTime.now());
        return invoiceRepository.save(invoiceEntity);
    }

    private InvoiceRequestBuilder invoiceRequestBuilder(String reference) {
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
                .reference(reference)
                .callbacks(notifyUrl, returnUrl);
    }
}
