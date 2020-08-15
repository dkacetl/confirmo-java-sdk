package net.confirmo.appexample.controller;

import net.confirmo.appexample.business.InvoiceManager;
import net.confirmo.appexample.form.InvoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private InvoiceManager invoiceManager;

    @GetMapping("/")
    public String showInvoiceForm(Model model) {
        return "forward:invoiceForm";
    }
}

