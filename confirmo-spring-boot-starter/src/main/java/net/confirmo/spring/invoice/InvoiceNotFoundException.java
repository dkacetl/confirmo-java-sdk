package net.confirmo.spring.invoice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvoiceNotFoundException extends RuntimeException {
    private String id;

    public InvoiceNotFoundException(String id) {
        this.id = id;
    }
}


