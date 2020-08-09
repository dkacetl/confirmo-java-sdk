package net.confirmo.spring.invoice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvoiceException extends RuntimeException {
    private String id;

    public InvoiceException(String id) {
        this.id = id;
    }

    public InvoiceException(String message, Throwable cause, String id) {
        super(message, cause);
        this.id = id;
    }

    public InvoiceException(Throwable cause, String id) {
        super(cause);
        this.id = id;
    }
}


