package net.confirmo.appexample;

import net.confirmo.client.restapi.EnableConfirmo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfirmo
@Import({ConfirmoPayExampleProperties.class})
public class ConfirmoPayExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmoPayExampleApplication.class, args);
	}

}
