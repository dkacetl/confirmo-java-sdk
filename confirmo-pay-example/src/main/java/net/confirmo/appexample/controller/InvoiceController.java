package net.confirmo.appexample.controller;

import net.confirmo.api.model.CreateNewInvoiceResponse;
import net.confirmo.api.query.BitcoinPayStatus;
import net.confirmo.appexample.business.InvoiceManager;
import net.confirmo.appexample.form.InvoiceForm;
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
        CreateNewInvoiceResponse newInvoiceResponse = invoiceManager.createInvoice(
                invoiceForm.getAmount(), invoiceForm.getReference());
        LOGGER.info("Invoice created: {}",newInvoiceResponse);

        return "redirect:"+newInvoiceResponse.getUrl();
    }

    @GetMapping(value = "/invoiceReceived/{reference}")
    public String getInvoiceReceived(@PathVariable("reference") String reference,
                                     @RequestParam("bitcoinpay-status") BitcoinPayStatus bitcoinpayStatus,
                                     Model model)  {
        LOGGER.info("invoiceReceived: {}, {}.",reference, bitcoinpayStatus);
        model.addAttribute("reference", reference);
        model.addAttribute("bitcoinpay-status", bitcoinpayStatus);
        return "invoicePaid";
    }
}

