package org.confirmo.appexample.controller;

import org.confirmo.appexample.business.InvoiceManager;
import org.confirmo.appexample.form.ConfirmationData;
import org.confirmo.appexample.form.InvoiceForm;
import org.confirmo.client.restapi.InvoiceService;
import org.confirmo.client.restapi.invoice.Invoice;
import org.confirmo.client.restapi.invoice.InvoiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private InvoiceManager invoiceManager;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public String showInvoiceForm(Model model) {
        model.addAttribute("invoiceForm", new InvoiceForm());
        return "invoiceForm";
    }

    @PostMapping(value = "/createInvoice")
    public String createInvoice(@ModelAttribute InvoiceForm invoiceForm, Model model) {
        Invoice invoice = invoiceManager.createInvoice(
                invoiceForm.getAmount(), invoiceForm.getReference());
        return "redirect:"+invoice.getUrl();
    }

    @GetMapping("/invoiceNotification")
    public String getInvoiceNotification(HttpServletRequest httpRequest) {
        return "invoicePaid";
    }

    @PostMapping("/invoiceNotification")
    public String postInvoiceNotification(HttpServletRequest httpRequest) {
        return "invoicePaid";
    }

    @GetMapping(value = "/invoiceRecieved/{reference}") // THIS IS IT
    public String getInvoiceRecieved(@PathVariable("reference") String reference,
                                     @RequestParam("bitcoinpay-status") String bitcoinpayStatus,
                                     Model model)  {
        model.addAttribute("reference", reference);
        model.addAttribute("bitcoinpay-status", bitcoinpayStatus);
        return "invoicePaid";
    }
}

