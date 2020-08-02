package net.confirmo.appexample.business;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceResponse;
import net.confirmo.api.tools.InvoiceRequestBuilder;
import net.confirmo.appexample.ConfirmoPayExampleProperties;
import net.confirmo.appexample.db.InvoiceRepository;
import net.confirmo.appexample.model.InvoiceEntity;
import net.confirmo.spring.invoice.InvoiceService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InvoiceManager {

    private InvoiceService invoiceService;

    private ConfirmoPayExampleProperties confirmoPayExampleProperties;

    private InvoiceRequestBuilderService invoiceRequestBuilderService;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceManager(InvoiceService invoiceService, ConfirmoPayExampleProperties confirmoPayExampleProperties, InvoiceRequestBuilderService invoiceRequestBuilderService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.confirmoPayExampleProperties = confirmoPayExampleProperties;
        this.invoiceRequestBuilderService = invoiceRequestBuilderService;
        this.invoiceRepository = invoiceRepository;
    }


    /**
     *
     * @param amount
     * @param reference
     * @return
     */
    public CreateNewInvoiceResponse createInvoice(float amount, String reference) {

        CreateNewInvoiceRequest invoiceRequest = createBuilder(reference)
                .product("Confirmo product example","Pleas pay for me, "+reference)
                .invoiceAmount(amount)
                .build();

        createRecord(amount, reference);

        return invoiceService.create(invoiceRequest);
    }

    private void createRecord(float amount, String reference) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setAmount(amount);
        invoiceEntity.setId(reference);
        invoiceEntity.setStatus("creating");
        invoiceRepository.save(invoiceEntity);
    }

    private InvoiceRequestBuilder createBuilder(String reference) {
        return invoiceRequestBuilderService.createBuilder()
                .reference(reference,
                        confirmoPayExampleProperties.getNotifyUrl() + "/"+reference,
                        confirmoPayExampleProperties.getReturnUrl() + "/"+reference);
    }
}
