package org.confirmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ConfirmoPayExampleProperties.class)
public class ConfirmoPayExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmoPayExampleApplication.class, args);
	}

}
