package api.pedidos.controller;

import api.pedidos.client.KeycloakClient;
import api.pedidos.dto.auth.AuthKeycloak;
import api.pedidos.dto.auth.AuthKeycloakDTO;
import api.pedidos.dto.auth.RefreshKeycloak;
import api.pedidos.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController /*implements AuthDocumentation*/ {

    @Autowired
    KeycloakClient client;
    @Autowired
    AuthServiceImpl serviceImpl;

    //@Override
    @PostMapping("/token")
    public ResponseEntity<AuthKeycloakDTO> token(@RequestBody AuthKeycloak authKeycloak) {
        return ResponseEntity.ok(serviceImpl.tokenService(authKeycloak));
    }

    //@Override
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthKeycloakDTO> refreshToken(@RequestBody RefreshKeycloak refresh) {
        return ResponseEntity.ok(serviceImpl.refreshTokenService(refresh));
    }
}