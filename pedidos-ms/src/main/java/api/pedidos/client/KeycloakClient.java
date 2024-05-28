package api.pedidos.client;

import api.pedidos.dto.form.AuthKeycloak;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${spring.application.name}", url = "${url.generate.token}")
public interface KeycloakClient {

    @PostMapping(headers = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthKeycloak getDadosKeycloak(@RequestBody Object auth);
}