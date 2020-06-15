package org.dkacetl.confirmo.payexample.controller;

import org.dkacetl.confirmo.apiclient.invoice.InvoiceService;
import org.dkacetl.confirmo.payexample.form.SetupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/setup")
    public String greetingForm(Model model) {
        model.addAttribute("setupForm", new SetupForm());
        invoiceService.get();
        return "setup";
    }

    @PostMapping("/payout")
    public String greetingSubmit(@ModelAttribute SetupForm setupForm) {
        return "payout";
    }

}
