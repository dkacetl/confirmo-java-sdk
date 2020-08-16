# confirmo-pay-example

Full featured example for integration Spring Boot application with Confirmo gateway.

## Getting started

build whole Confirmo Java SDK by Maven.

## Setup

create ```./config/application.properties``` file and set properties as you wish

```properties
confirmo-pay-example.user=user
confirmo-pay-example.password=user01

logging.level.org.apache.http=DEBUG
confirmo.rest-api.url=https://confirmo.net/api/v3/

# callback (webhook) password, recommended to enable and fill it
confirmo.rest-api.callback-password=test

# Database in memory
spring.datasource.url=jdbc:h2:mem:inmemdb;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.open-in-view=true
spring.jpa.generate-ddl=false
spring.jpa.hibernate.ddl-auto=none
```

* Example is driven by Liquibase, so database model updating must be disabled.
* Example is protected by basic http security, see user/password
* Webhook (Callback) is set up.
* Inmemory H2 database used

## Start the app

1. In Confirmo-Pay-Example directory execute: 
```bash
mvn clean package
java -jar ./target/confirmo-pay-example-0.0.1-SNAPSHOT.jar
```
or
```bash
mvn clean package spring-boot:run
```

2. Open browser on ```http://localhost:8080```