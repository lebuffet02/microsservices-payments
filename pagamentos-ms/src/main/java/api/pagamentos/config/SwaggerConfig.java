package api.pagamentos.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SecurityScheme( name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
@OpenAPIDefinition(info = @Info(
        title = "PAGAMENTOS-MS",
        description = "Criação API de pagamentos",
        version = "1.0",
        contact = @Contact(
                name = "Lucas Buffet",
                email = "lebuffet02@gmail.com"
        ),
        license = @License(
                name = "Apache 2.0")), externalDocs = @ExternalDocumentation(url = "https://github.com/lebuffet02/microsservices-food"),
        servers = @Server(url = "${server.port}", description = "porta alterada"),
        security = {
                @SecurityRequirement(
                        name = "accessToken")
        }
)
public class SwaggerConfig implements WebMvcConfigurer {}
