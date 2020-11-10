# Confirmo Spring-Boot-Starter

Ready to use "Starter" for Spring-Boot framework 2.3.x

Add to `pom.xml`

```xml
<dependency>
	<groupId>eu.dkacetl</groupId>
	<artifactId>confirmo-spring-boot-starter</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

Prepare `application.properties`
```properties
confirmo.rest-api.url=https://confirmo.net/api/v3/
confirmo.rest-api.apiKey=see_the_confirmo_doc
confirmo.rest-api.callbackPassword=see_the_confirmo_doc
```

Enable Confirmo support in your app
```java
@SpringBootApplication
@EnableConfirmo
public class ExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
}
```

Use the api:
```java
CreateNewInvoiceRequest invoiceRequest = new CreateNewInvoiceRequest();
Product product = new Product();

product.setName("Test");
product.setDescription("Desc");

Settlement settlement = new Settlement();
settlement.setCurrency("CZK");

Invoice invoice = new Invoice();
invoice.setAmount(format.format(amount));
invoice.setCurrencyFrom("CZK");
invoice.setCurrencyTo("LTC");

invoiceRequest.setProduct(product);
invoiceRequest.setInvoice(invoice);
invoiceRequest.setSettlement(settlement);
invoiceRequest.setReference(reference);
invoiceRequest.setNotifyUrl(confirmoPayExampleProperties.getNotifyUrl() + "/"+reference);
invoiceRequest.setReturnUrl(confirmoPayExampleProperties.getReturnUrl() + "/"+reference);

invoiceService.post(invoiceRequest);
```