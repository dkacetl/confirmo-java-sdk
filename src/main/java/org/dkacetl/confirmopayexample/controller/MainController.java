package org.dkacetl.confirmopayexample.controller;

import org.dkacetl.confirmopayexample.model.Setup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/setup")
    public String greetingForm(Model model) {
        model.addAttribute("setup", new Setup());
        return "setup";
    }

    @PostMapping("/payout")
    public String greetingSubmit(@ModelAttribute Setup setup) {
        return "payout";
    }

}
