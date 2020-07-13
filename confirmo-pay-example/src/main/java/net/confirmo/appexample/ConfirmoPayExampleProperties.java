package net.confirmo.appexample;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "confirmo-pay-example")
public class ConfirmoPayExampleProperties {

    private String user;
    private String password;
    private String notifyUrl = "http://127.0.0.1:8080/invoiceNotification";
    private String returnUrl = "http://127.0.0.1:8080/invoiceReceived";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
