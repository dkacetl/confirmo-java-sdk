package org.confirmo.appexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;

@Controller
public class InvoiceNotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceNotificationController.class);

    // UNAUTHENTICATED ENDPOINT - WEBHOOK from confirmo
    @PostMapping(
            value = "/invoiceNotification/{reference}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> invoiceNotification(@PathParam("reference") String reference, RequestEntity<String> requestEntity) {
        LOGGER.warn("invoice webhook notification !!!!!!!!!! : {}, {}", reference, requestEntity.toString());
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
