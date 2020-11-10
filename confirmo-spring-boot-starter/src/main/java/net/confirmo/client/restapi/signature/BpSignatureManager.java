package net.confirmo.client.restapi.signature;

public interface BpSignatureManager {

    String computeBpSignature(String content, String password);
}
