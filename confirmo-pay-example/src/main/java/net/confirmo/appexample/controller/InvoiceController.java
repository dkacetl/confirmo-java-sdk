package net.confirmo.appexample.controller;

import net.confirmo.appexample.business.InvoiceManager;
import net.confirmo.appexample.form.InvoiceForm;
import net.confirmo.appexample.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceManager invoiceManager;

    @PostMapping(value = "/createInvoice")
    public String createInvoice(@ModelAttribute InvoiceForm invoiceForm, Model model) {
        String id = invoiceManager.generateInvoiceId();

        Invoice invoice = invoiceManager.createInvoice(id, invoiceForm.getAmount(),
                invoiceForm.getTargetCryptocurrencySelection().getCurrency());

        LOGGER.info("Invoice created: {}", invoice.getInvoiceDetailResponse());

        return "redirect:"+invoice.getInvoiceDetailResponse().getUrl();
    }

}

