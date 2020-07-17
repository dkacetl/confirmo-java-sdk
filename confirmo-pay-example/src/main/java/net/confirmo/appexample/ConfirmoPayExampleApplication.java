package net.confirmo.appexample;

import net.confirmo.client.restapi.EnableConfirmo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfirmo
@Import({ConfirmoPayExampleProperties.class})
public class ConfirmoPayExampleApplication {

	public static void main(String[] args) {
		// It is a global-world service, set UTC as local time.
		// all date/times will be stored in UTC.
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ConfirmoPayExampleApplication.class, args);
	}

}
