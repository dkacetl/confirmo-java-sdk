package net.confirmo.api.signature;

public interface BpSignatureManager {

    String computeBpSignature(String content, String password);
}
