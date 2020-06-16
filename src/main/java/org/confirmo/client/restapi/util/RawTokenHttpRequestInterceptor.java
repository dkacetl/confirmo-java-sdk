package org.confirmo.client.restapi.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 */
public class RawTokenHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    public static final String BEARER = "Bearer "; // space is important

    private String rawToken;

    public RawTokenHttpRequestInterceptor(String rawToken) {
        this.rawToken = rawToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, BEARER + rawToken);

        // Prevent weird 403 forbidden
        httpRequest.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpRequest.getHeaders().add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        // ---

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    public String getRawToken() {
        return rawToken;
    }

    public void setRawToken(String rawToken) {
        this.rawToken = rawToken;
    }
}
