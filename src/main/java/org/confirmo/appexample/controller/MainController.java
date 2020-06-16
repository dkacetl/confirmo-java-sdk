package org.confirmo.appexample.controller;

import org.confirmo.appexample.business.InvoiceManager;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/orderRecieved")
    public String post(HttpRequest httpRequest) {
        return "setup";
    }
}
