package net.confirmo.appexample.security;

public interface ReCaptchaService {
     boolean isValid(String responseToken);
}
