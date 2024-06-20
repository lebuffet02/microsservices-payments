package eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.Locale;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

	Logger LOGGER = LoggerFactory.getLogger(EurekaApplication.class);

	@Bean
	public CommandLineRunner initializeLogs() {
		return (args) -> {
			LOGGER.info("-------------SUBIU-------------");
			LOGGER.info(String.valueOf(new Date()).concat(new Locale(" pt", "BR").toString()));
		};
	}
}
