package net.confirmo.appexample.business;

import net.confirmo.api.model.Currency;
import net.confirmo.api.query.BitcoinPayStatus;
import net.confirmo.appexample.model.Invoice;
import net.confirmo.spring.invoice.InvoiceNotFoundException;

public interface InvoiceManager {

    String generateInvoiceId();

    Invoice createInvoice(String id, float amount, Currency targetCryptocurrency);

    Invoice loadInvoice(String id) throws InvoiceNotFoundException;

    Invoice synchronize(Invoice invoice);

    Invoice handleInvoiceCallback(String id, BitcoinPayStatus bitcoinPayStatus);

}
