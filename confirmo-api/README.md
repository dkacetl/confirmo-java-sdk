# Confirmo.net API for Java

[Confirmo.net](https://confirmo.net) provides REST API in form of unformal documentation and set of json schemas. 
More comfortable is to have full featured OpenAPI specification.

This module offers subset of API in form of [Confirmo.net OpenAPI specification](./src/main/resources/openapi/confirmo-api.yaml) as well as auto-generated Java POJO classes and 
native client (no framework). 
  
### How to use

Add maven dependency

```xml
<dependecy>
  <groupId>eu.dkacetl</groupId>
  <artifactId>confirmo-api</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependecy>
```

Then is possible to perform API calls:

```java
public class Application {

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

```

### Spring support

For comfort usage with Spring Framework, please see [confirmo-spring-boot-starter](./confirmo-spring-boot-starter)
