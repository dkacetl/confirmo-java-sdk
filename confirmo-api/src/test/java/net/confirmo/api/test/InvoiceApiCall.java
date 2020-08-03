package net.confirmo.api.test;

import net.confirmo.api.client.InvoiceApi;
import net.confirmo.api.client.invoker.ApiClient;
import net.confirmo.api.client.invoker.ApiException;
import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.Currency;
import net.confirmo.api.tools.InvoiceRequestBuilder;
import org.junit.Test;

public class InvoiceApiCall {

    public void nativeClientCall() throws ApiException {
        ApiClient apiClient = new ApiClient();
        apiClient.setRequestInterceptor(builder -> builder.header("Authorization","Bearer <KEY>"));
        InvoiceApi invoiceApi = new InvoiceApi(apiClient);

        CreateNewInvoiceRequest createNewInvoiceRequest =
                new InvoiceRequestBuilder().product("Test","Descr")
                .invoice(Currency.CZK, 15.0f, Currency.BTC)
                .settlement(Currency.CZK)
                .reference("ref001", "https://127.0.0.1:8080/notify","https://127.0.0.1:8080/return").build();

        try {
            invoiceApi.createNewInvoice(createNewInvoiceRequest);
        } catch (ApiException e) {
            System.out.println(e.getCode() + ":" + e.getResponseBody());
            throw e;
        }
    }
}
