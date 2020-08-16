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

Use the Confirmo API easy in your Spring Boot Controller:
```java
@Controller
public class MyInvoiceController {

    // helps to create invoice request by "builder" design pattern
    @Autowired
    private InvoiceRequestBuilderFactory invoiceRequestBuilderFactory;
    
    @Autowired
    private InvoiceService invoiceService;
    
    @GetMapping("/createNewInvoice")
    private String createNewInvoiceController() {

        CreateNewInvoiceRequest request  = 
                invoiceRequestBuilderFactory.create()
                .product("Confirmo product","Please pay 1CZK for me in LTC.")
                .invoiceAmount(1.0f).invoiceCurrency(Currency.CZK, Currency.LTC) 
                .callbacks("https://public-url/webhook/",
                           "https://return-to-merchant-url")
                .build();
        
        // Note:
        // https://public-url/webhook/ is accessible endpoint from internet for callback notifications

        InvoiceDetailResponse invoiceDetailResponse = invoiceService.create(request);
        
        // Go to confirmo.net gateway page with QR code for pay
        return "redirect:" + invoiceDetailResponse.getUrl();
    }
}

```