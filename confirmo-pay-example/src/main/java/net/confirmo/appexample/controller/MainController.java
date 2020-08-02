package net.confirmo.appexample.controller;

import net.confirmo.appexample.form.InvoiceForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showInvoiceForm(Model model) {
        model.addAttribute("invoiceForm", new InvoiceForm());
        return "invoiceForm";
    }
}

