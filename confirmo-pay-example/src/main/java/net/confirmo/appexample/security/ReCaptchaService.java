package net.confirmo.appexample.security;

import java.io.IOException;

public interface ReCaptchaService {
     boolean isValid(String responseToken);
}
