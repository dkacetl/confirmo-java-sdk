package net.confirmo.appexample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.confirmo.api.model.WebhookRequest;
import net.confirmo.api.query.BitcoinPayStatus;
import net.confirmo.spring.signature.RequestEntityValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

@Controller
public class WebhookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired(required = false)
    private RequestEntityValidator requestEntityValidator;

    // UNAUTHENTICATED ENDPOINT - WEBHOOK from confirmo
    @PostMapping(
            value = "/invoiceNotification/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> invoiceNotification(@PathParam("id") String id, RequestEntity<String> requestEntity) {
        // validate if validator is present (webhook bp-signature validation)
        if (requestEntityValidator!=null) {
            requestEntityValidator.validate(requestEntity);
        }

        LOGGER.warn("invoice webhook notification !!!!!!!!!! : {}, {}", id, requestEntity.toString());
        WebhookRequest webhookRequest = parseWebhookRequest(requestEntity.getBody());
        LOGGER.warn("webhook : {}", webhookRequest.toString());

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(value = "/invoiceReceived/{id}")
    public String getInvoiceReceived(@PathVariable("id") String id,
                                     @RequestParam("bitcoinpay-status") BitcoinPayStatus bitcoinpayStatus,
                                     Model model)  {
        LOGGER.info("invoiceReceived: {}, {}.",id, bitcoinpayStatus);
        model.addAttribute("id", id);
        model.addAttribute("bitcoinpay-status", bitcoinpayStatus);
        return "invoicePaid";
    }

    /**
     *
     * @param content
     * @return
     */
    private WebhookRequest parseWebhookRequest(String content)  {
        if (content==null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Illegal content");
        }
        try {
            return objectMapper.readValue(content, WebhookRequest.class);
        } catch (JsonProcessingException e) {
            LOGGER.debug("");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Illegal content");
        }
    }

    @PostConstruct
    public void information() {
        if (requestEntityValidator==null) {
            LOGGER.warn("requestEntityValidator is not present, probably callback-password in not set. Improve security and enable callback webhook-password.");
        }
    }
}
