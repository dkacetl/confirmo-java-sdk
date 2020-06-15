package org.dkacetl.confirmo.apiclient;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RawTokenHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private String rawToken;

    public RawTokenHttpRequestInterceptor(String rawToken) {
        this.rawToken = rawToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().add("Authorization", "Bearer "+ rawToken);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    public String getRawToken() {
        return rawToken;
    }

    public void setRawToken(String rawToken) {
        this.rawToken = rawToken;
    }
}
