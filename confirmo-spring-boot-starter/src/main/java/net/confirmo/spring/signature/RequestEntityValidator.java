package net.confirmo.spring.signature;

import org.springframework.http.RequestEntity;
import org.springframework.web.server.ResponseStatusException;

public interface RequestEntityValidator {
    void validate(RequestEntity<?> requestEntity) throws ResponseStatusException;
}
