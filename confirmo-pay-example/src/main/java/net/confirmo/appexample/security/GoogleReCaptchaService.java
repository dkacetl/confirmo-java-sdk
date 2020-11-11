package net.confirmo.appexample.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class GoogleReCaptchaService implements ReCaptchaService {

    private static final String RECAPTCHA_SERVICE_URL = "https://www.google.com/recaptcha/api/siteverify";

    @Autowired
    private GoogleReCaptchaProperties googleReCaptchaProperties;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * checks if a user is valid
     * @param responseToken
     * @return true if human, false if bot
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public boolean isValid(String responseToken) throws ReCaptchaValidationException {
        if (responseToken == null || "".equals(responseToken)) {
            return false;
        }

        try {
            URL obj = new URL(RECAPTCHA_SERVICE_URL);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            //add client result as post parameter
            String postParams =
                    "secret=" + googleReCaptchaProperties.getSecretKey() +
                            "&response=" + responseToken;

            // send post request to google recaptcha server
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Parse JSON-response
            GoogleVerificationObject googleVerificationObject = objectMapper.readValue(response.toString(), GoogleVerificationObject.class);

            //result should be sucessfull and spam score above 0.5
            return (googleVerificationObject.isSuccess() && googleVerificationObject.getScore() >= 0.5);
        } catch (IOException e) {
            throw new ReCaptchaValidationException("Internal server error",e);
        }
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setGoogleReCaptchaProperties(GoogleReCaptchaProperties googleReCaptchaProperties) {
        this.googleReCaptchaProperties = googleReCaptchaProperties;
    }
}
