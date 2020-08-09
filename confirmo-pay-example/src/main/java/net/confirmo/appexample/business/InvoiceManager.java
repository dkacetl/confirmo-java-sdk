package net.confirmo.appexample.business;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceResponse;
import net.confirmo.api.model.Currency;
import net.confirmo.api.model.InvoiceDetailResponse;
import net.confirmo.api.query.BitcoinPayStatus;
import net.confirmo.api.tools.InvoiceRequestBuilder;
import net.confirmo.appexample.ConfirmoPayExampleProperties;
import net.confirmo.appexample.db.InvoiceEntity;
import net.confirmo.appexample.db.InvoiceRepository;
import net.confirmo.appexample.db.InvoiceStatus;
import net.confirmo.appexample.model.Invoice;
import net.confirmo.spring.invoice.InvoiceNotFoundException;
import net.confirmo.spring.invoice.InvoiceService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
public class InvoiceManager {

    private InvoiceService invoiceService;

    private ConfirmoPayExampleProperties confirmoPayExampleProperties;

    private InvoiceRequestBuilderFactory invoiceRequestBuilderFactory;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceManager(InvoiceService invoiceService, ConfirmoPayExampleProperties confirmoPayExampleProperties, InvoiceRequestBuilderFactory invoiceRequestBuilderFactory, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.confirmoPayExampleProperties = confirmoPayExampleProperties;
        this.invoiceRequestBuilderFactory = invoiceRequestBuilderFactory;
        this.invoiceRepository = invoiceRepository;
    }

    public String generateInvoiceId() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public Invoice handleInvoiceCallback(String id, BitcoinPayStatus bitcoinPayStatus) {

        // todo: validate access to id

        // we cannot load invoice detail from confirmo, so just mark
//        switch (bitcoinPayStatus) {
//            // automatically cancel invoice
//            case prepared:
//            case canceled:
//            case expired: {
//                return markStatus(id, InvoiceStatus.valueOf(bitcoinPayStatus.name()));
//            }
//        }

        // get all data for invoice, also from confirmo API
        // it is secure
        Invoice invoice = loadInvoice(id);

        if (invoice.getInvoiceDetailResponse()==null) {
            throw new IllegalStateException("Invoice is not ready yet.");
        }

        // get real status
        InvoiceDetailResponse.StatusEnum statusEnum
                = invoice.getInvoiceDetailResponse().getStatus();

        // mark status
        return markStatus(id, InvoiceStatus.valueOf(statusEnum.getValue().toString()));
    }

    /**
     * Full load invoice from db as well as from confirmo web.
     *
     * @param id id of invoice
     * @return
     */
    public Invoice loadInvoice(String id) {
        // Load from db
        InvoiceEntity invoiceEntity = invoiceRepository
                .findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));

        String confirmoInvoiceId = invoiceEntity.getConfirmoInvoiceId();

        InvoiceDetailResponse response = invoiceService.getOne(confirmoInvoiceId);

        return new Invoice(invoiceEntity, response);
    }

    @Transactional
    public Invoice markStatus(String id, InvoiceStatus invoiceStatus) {
        InvoiceEntity invoiceEntity = invoiceRepository
                .findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));
        invoiceEntity.setStatus(invoiceStatus);
        invoiceRepository.saveAndFlush(invoiceEntity);
        return new Invoice(invoiceEntity);
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

        CreateNewInvoiceResponse createNewInvoiceResponse = invoiceService.create(invoiceRequest);
        InvoiceDetailResponse invoiceDetailResponse = new InvoiceDetailResponse();
        BeanUtils.copyProperties(createNewInvoiceResponse, invoiceDetailResponse);

        updateInitialConfirmoData(invoiceEntity, createNewInvoiceResponse);

        return new Invoice(invoiceEntity, invoiceDetailResponse);
    }

    @Transactional
    public InvoiceEntity updateInitialConfirmoData(InvoiceEntity invoiceEntity, CreateNewInvoiceResponse createNewInvoiceResponse) {
        invoiceEntity.setConfirmoInvoiceId(createNewInvoiceResponse.getId());
        invoiceEntity.setStatus(InvoiceStatus.prepared);
        return invoiceRepository.saveAndFlush(invoiceEntity);
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
        return invoiceRequestBuilderFactory.create()
                .reference(reference,
                        confirmoPayExampleProperties.getNotifyUrl() + "/"+reference,
                        confirmoPayExampleProperties.getReturnUrl() + "/"+reference);
    }
}
