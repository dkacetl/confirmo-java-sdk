package net.confirmo.appexample;

import net.confirmo.client.restapi.EnableConfirmo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:/fake-credentials.properties")
@EnableConfirmo
class ConfirmoPayExampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
