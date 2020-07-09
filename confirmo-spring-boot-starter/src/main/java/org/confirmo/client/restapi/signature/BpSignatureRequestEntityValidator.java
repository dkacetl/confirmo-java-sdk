package org.confirmo.client.restapi.signature;

import org.confirmo.client.restapi.ConfirmoApiClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Validate request bp-signature
 *
 * https://confirmonetapi.docs.apiary.io/#introduction/webhooks
 */
public class BpSignatureRequestEntityValidator implements RequestEntityValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BpSignatureRequestEntityValidator.class);


    private BpSignatureManager bpSignatureManager;

    private ConfirmoApiClientProperties confirmoApiClientProperties;

    @Autowired
    public BpSignatureRequestEntityValidator(BpSignatureManager bpSignatureManager, ConfirmoApiClientProperties confirmoApiClientProperties) {
        this.bpSignatureManager = bpSignatureManager;
        this.confirmoApiClientProperties = confirmoApiClientProperties;
    }

    /**
     * check if bp-signature placed in header is valid
     *
     * @param requestEntity request for bp-signature validation
     * @throws ResponseStatusException validation error
     */
    @Override
    public void validate(RequestEntity<?> requestEntity) throws ResponseStatusException {
        // extract bp-signature
        String receivedBpSignature = extractBpSignature(requestEntity);
        LOGGER.info("BP-SIGNATURE received:   {}", receivedBpSignature);

        Object content = requestEntity.getBody();
        if (content!=null) {
            // calculate signature
            String calculatedBpSignature = bpSignatureManager.computeBpSignature(
                    content.toString(),
                    confirmoApiClientProperties.getCallbackPassword());
            LOGGER.info("BP-SIGNATURE calculated: {}",calculatedBpSignature);

            if (calculatedBpSignature.equals(receivedBpSignature)) {
                return; // all is ok - signature is valid
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid signature");
    }

    private String extractBpSignature(RequestEntity<?> requestEntity) {
        List<String> bpSignatures = requestEntity.getHeaders().get("bp-signature");
        if (bpSignatures!=null) {
            if (bpSignatures.get(0)!=null) {
                LOGGER.debug("Bp-signature of request {}",  bpSignatures.get(0));
                return bpSignatures.get(0);
            }
        }
        LOGGER.debug("Bp-signature of request is missing.");
        return ""; // return empty string
    }
}
