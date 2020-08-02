package net.confirmo.spring.signature;

public interface BpSignatureManager {

    String computeBpSignature(String content, String password);
}
