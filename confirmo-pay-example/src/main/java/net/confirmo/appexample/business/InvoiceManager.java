package net.confirmo.appexample.business;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceResponse;
import net.confirmo.api.model.Currency;
import net.confirmo.api.model.InvoiceDetailResponse;
import net.confirmo.api.tools.InvoiceRequestBuilder;
import net.confirmo.appexample.ConfirmoPayExampleProperties;
import net.confirmo.appexample.db.InvoiceRepository;
import net.confirmo.appexample.db.InvoiceEntity;
import net.confirmo.appexample.model.Invoice;
import net.confirmo.spring.invoice.InvoiceService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     *
     * @param amount
     * @param id
     * @return
     */
    public Invoice createInvoice(String id, float amount, Currency targetCryptocurrency) {

        CreateNewInvoiceRequest invoiceRequest = createBuilder(id)
                .product("Confirmo product example","Pleas pay for me, "+id)
                .invoice(Currency.CZK, amount, targetCryptocurrency)
                .build();

        CreateNewInvoiceResponse createNewInvoiceResponse = invoiceService.create(invoiceRequest);

        InvoiceEntity invoiceEntity = createRecord(id, amount, targetCryptocurrency);

        InvoiceDetailResponse invoiceDetailResponse = new InvoiceDetailResponse();
        BeanUtils.copyProperties(createNewInvoiceResponse, invoiceDetailResponse);

        return new Invoice(invoiceEntity, invoiceDetailResponse);
    }

    private InvoiceEntity createRecord(String id, float amount, Currency currency) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(amount);
        invoiceEntity.setId(id);
        invoiceEntity.setStatus("creating");
        invoiceEntity.setCurrency(currency);
        return invoiceRepository.save(invoiceEntity);
    }

    private InvoiceRequestBuilder createBuilder(String reference) {
        return invoiceRequestBuilderFactory.create()
                .reference(reference,
                        confirmoPayExampleProperties.getNotifyUrl() + "/"+reference,
                        confirmoPayExampleProperties.getReturnUrl() + "/"+reference);
    }
}
