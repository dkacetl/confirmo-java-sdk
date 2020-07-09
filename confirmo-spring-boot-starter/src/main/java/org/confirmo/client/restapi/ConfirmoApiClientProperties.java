package org.confirmo.client.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "confirmo.rest-api")
public class ConfirmoApiClientProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmoApiClientProperties.class);

    // access key to confirmo api
    private String apiKey;

    // default url to confirmo
    private String url = "https://confirmo.net/api/v3/";

    // protection of webhook endpoint ("unprotected open endpoint")
    // based on content hashing
    private String callbackPassword;

    // ---------------------------------------------------------------------------------------

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCallbackPassword() {
        return callbackPassword;
    }

    public void setCallbackPassword(String callbackPassword) {
        this.callbackPassword = callbackPassword;
    }

    @PostConstruct
    public void checkApiKey() {
        if (StringUtils.isEmpty(apiKey)) {
            LOGGER.warn("ApiKey is empty, Confirmo REST API client will not work!!!!!!");
        }
        if (StringUtils.isEmpty(callbackPassword)) {
            LOGGER.warn("callbackPassword is empty, webhooks will not be so secure.");
        }
    }

}
