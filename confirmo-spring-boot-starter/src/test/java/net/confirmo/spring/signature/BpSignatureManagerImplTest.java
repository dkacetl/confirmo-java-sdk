package net.confirmo.spring.signature;


import net.confirmo.api.signature.BpSignatureManager;
import net.confirmo.api.signature.Sha256BasedBpSignatureManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BpSignatureManagerImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BpSignatureManagerImplTest.class);

    private static final String testedContent = "This is content";
    private static final String testedPassword = "pwd";
    private static final String expectedBpSignature = "5370a5a53435184830d0b8b8e8718c6e5b6c91fe9d3d2f06a5d75f695b841038";

    @Test
    public void signingTest() {
        BpSignatureManager bpSignatureManager = new Sha256BasedBpSignatureManager();
        String signature = bpSignatureManager.computeBpSignature(testedContent,testedPassword);
        Assertions.assertThat(signature).isEqualTo(expectedBpSignature);
    }
}
