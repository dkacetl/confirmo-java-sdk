package net.confirmo.appexample.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="ReCaptcha Validation Error")  // 404
public class ReCaptchaValidationException extends RuntimeException {
    public ReCaptchaValidationException() {
    }

    public ReCaptchaValidationException(String message) {
        super(message);
    }

    public ReCaptchaValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReCaptchaValidationException(Throwable cause) {
        super(cause);
    }

    public ReCaptchaValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
