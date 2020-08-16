# Confirmo.net Java SDK

[Confirmo.net](https://confirmo.net) is payment gateway which helps you to enable for your e-shop an acceptation of crypto-currencies.
Right now, there aren't available official integration for Java language. Confirmo Java SDK is independent tool for easy use 
Confirmo.net in Java and Spring applications.

### Modules

* [confirmo-api](./confirmo-api) - Framework agnostic, pure Java POJOs and interfaces for easy REST API implementation
* [confirmo-spring-boot-starter](./confirmo-spring-boot-starter) - Ready-to-use library for call API of Confirmo.net
* [confirmo-pay-example](./confirmo-pay-example) - Simple application for demonstrate features of SDK

## Getting started

Build project by **Maven** (3.5.x or higher) and **Java 11** (or higher). For example

```bash
JAVA_HOME=/opt/java/jdk-11.0.2/
MAVEN_HOME=/opt/maven/apache-maven-3.5.3/

$MAVEN_HOME/bin/mvn clean install
```
When build accomplished, you can start with [Confirmo Spring Boot Starter documentation](./confirmo-spring-boot-starter) or try to run
[Confirmo Pay Example](./confirmo-pay-example).
   