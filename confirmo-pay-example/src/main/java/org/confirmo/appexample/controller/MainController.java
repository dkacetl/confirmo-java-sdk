package org.confirmo.appexample.controller;

import org.confirmo.appexample.business.InvoiceManager;
import org.confirmo.appexample.form.InvoiceForm;
import org.confirmo.client.restapi.InvoiceService;
import org.confirmo.client.restapi.schema.CreateNewInvoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}

