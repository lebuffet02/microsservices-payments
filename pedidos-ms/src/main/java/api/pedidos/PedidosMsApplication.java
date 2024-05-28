package api.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableFeignClients
@EntityScan(basePackages = "api.pedidos.entity")
@EnableJpaRepositories(basePackages = "api.pedidos.repository")
@SpringBootApplication
public class PedidosMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosMsApplication.class, args);
	}
}