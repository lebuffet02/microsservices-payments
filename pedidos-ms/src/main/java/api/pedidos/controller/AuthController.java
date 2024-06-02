package api.pedidos.controller;

import api.pedidos.client.KeycloakClient;
import api.pedidos.documentation.AuthDocumentation;
import api.pedidos.dto.AuthKeycloakDTO;
import api.pedidos.dto.form.AuthKeycloak;
import api.pedidos.dto.form.RefreshKeycloak;
import api.pedidos.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthDocumentation {

    @Autowired
    KeycloakClient client;
    @Autowired
    AuthServiceImpl serviceImpl;

    @Override
    @PostMapping("/token")
    public ResponseEntity<AuthKeycloakDTO> token(@RequestBody AuthKeycloak authKeycloak) {
        return ResponseEntity.ok(serviceImpl.tokenService(authKeycloak));
    }

    @Override
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthKeycloakDTO> refreshToken(@RequestBody RefreshKeycloak refresh) {
        return ResponseEntity.ok(serviceImpl.refreshTokenService(refresh));
    }
}