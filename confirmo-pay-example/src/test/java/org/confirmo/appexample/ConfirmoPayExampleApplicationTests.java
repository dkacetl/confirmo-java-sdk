package org.confirmo.appexample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:/fake-credentials.properties")
class ConfirmoPayExampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
