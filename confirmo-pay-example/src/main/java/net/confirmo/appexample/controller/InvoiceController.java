package net.confirmo.appexample.controller;

import net.confirmo.api.query.BitcoinPayStatus;
import net.confirmo.appexample.business.InvoiceManager;
import net.confirmo.appexample.db.InvoiceRepository;
import net.confirmo.appexample.form.InvoiceForm;
import net.confirmo.appexample.model.Invoice;
import net.confirmo.appexample.model.PaginationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceManager invoiceManager;

    @GetMapping("/invoiceForm")
    public String showInvoiceForm(Model model) {
        model.addAttribute("invoiceForm", new InvoiceForm());
        return "invoiceForm";
    }

    @PostMapping(value = "/createInvoice")
    public String createInvoice(@ModelAttribute InvoiceForm invoiceForm, Model model) {

        String id = invoiceManager.generateInvoiceId();

        Invoice invoice = invoiceManager.createInvoice(
                id,
                invoiceForm.getAmount(),
                invoiceForm.getTargetCryptocurrencySelection().getCurrency());

        LOGGER.info("Invoice created: {}", invoice.getInvoiceDetailResponse());

        return "redirect:" + invoice.getInvoiceDetailResponse().getUrl();
    }

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping(value = "/invoices")
    public String getInvoices(PaginationData paginationData, Model model) {
        // TODO: validate data

        List<Invoice> invoices = invoiceManager.getAll(paginationData);

        model.addAttribute("invoices", invoices);
        model.addAttribute("count",invoiceRepository.count());
        return "invoices";
    }

    @GetMapping(value = "/invoice/{id}")
    public String getInvoiceReceived(@PathVariable("id") String id,
                                     Model model)  {

        Invoice invoice = invoiceManager.handleInvoice(id);

        model.addAttribute("id", id);
        model.addAttribute("invoice",invoice);
        model.addAttribute("status", invoice.getStatus());

        return "invoiceDetail";
    }

    @GetMapping(value = "/invoiceReceived/{id}")
    public String getInvoiceReceived(@PathVariable("id") String id,
                                     @RequestParam("bitcoinpay-status") BitcoinPayStatus bitcoinpayStatus,
                                     Model model)  {
        LOGGER.info("invoiceReceived: {}, {}.",id, bitcoinpayStatus);

        Invoice invoice = invoiceManager.handleInvoice(id);

        model.addAttribute("id", id);
        model.addAttribute("invoice",invoice);
        model.addAttribute("status", invoice.getStatus());

        return "invoiceDetail";
    }

}