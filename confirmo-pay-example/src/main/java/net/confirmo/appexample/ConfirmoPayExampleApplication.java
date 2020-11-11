package net.confirmo.appexample;

import net.confirmo.appexample.db.InvoiceRepository;
import net.confirmo.appexample.security.GoogleReCaptchaProperties;
import net.confirmo.spring.EnableConfirmo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfirmo
@EnableJpaRepositories(basePackageClasses = {InvoiceRepository.class})
@EnableTransactionManagement
@EnableConfigurationProperties({ConfirmoPayExampleProperties.class, GoogleReCaptchaProperties.class})
public class ConfirmoPayExampleApplication {

	public static void main(String[] args) {
		// It is a global-world service, set UTC as local time.
		// all date/times will be stored in UTC.
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplication springApplication = new SpringApplication(ConfirmoPayExampleApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
