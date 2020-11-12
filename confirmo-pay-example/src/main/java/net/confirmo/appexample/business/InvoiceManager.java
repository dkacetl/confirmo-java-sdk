package net.confirmo.appexample.business;

import net.confirmo.api.model.Currency;
import net.confirmo.appexample.model.Invoice;
import net.confirmo.appexample.model.PaginationData;
import net.confirmo.spring.invoice.InvoiceNotFoundException;

import java.util.List;

public interface InvoiceManager {

    String generateInvoiceId();

    Invoice createInvoice(String id, float amount, Currency targetCryptocurrency);

    Invoice fullLoadInvoice(String id) throws InvoiceNotFoundException;

    Invoice synchronize(Invoice invoice);

    Invoice handleInvoice(String id);

    List<Invoice> getAll(PaginationData paginationData);

}
