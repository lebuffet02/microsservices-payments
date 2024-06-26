package api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.Locale;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

	@Bean
	public CommandLineRunner initializeLogs() {
		return (args) -> {
			LOGGER.info("-------------SUBIU-------------");
			LOGGER.info(String.valueOf(new Date()).concat(new Locale(" pt", "BR").toString()));
		};
	}
}