package org.dkacetl.confirmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ConfirmoPayExampleConfigProperties.class)
public class ConfirmoPayExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmoPayExampleApplication.class, args);
	}

}
