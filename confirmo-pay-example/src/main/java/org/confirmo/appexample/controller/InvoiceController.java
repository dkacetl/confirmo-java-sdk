package org.confirmo.appexample.controller;

import org.confirmo.appexample.business.InvoiceManager;
import org.confirmo.appexample.form.InvoiceForm;
import org.confirmo.client.restapi.InvoiceService;
import org.confirmo.client.restapi.schema.CreateNewInvoiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceManager invoiceManager;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(value = "/createInvoice")
    public String createInvoice(@ModelAttribute InvoiceForm invoiceForm, Model model) {
        CreateNewInvoiceResponse newInvoiceResponse = invoiceManager.createInvoice(
                invoiceForm.getAmount(), invoiceForm.getReference());
        LOGGER.info("Invoice created: {}",newInvoiceResponse);

        return "redirect:"+newInvoiceResponse.getUrl();
    }

    @GetMapping(value = "/invoiceReceived/{reference}")
    public String getInvoiceReceived(@PathVariable("reference") String reference,
                                     @RequestParam("bitcoinpay-status") String bitcoinpayStatus,
                                     Model model)  {
        LOGGER.info("invoiceReceived: {}, {}.",reference, bitcoinpayStatus);
        model.addAttribute("reference", reference);
        model.addAttribute("bitcoinpay-status", bitcoinpayStatus);
        return "invoicePaid";
    }
}

