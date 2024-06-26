package api.pagamentos.controller;

import api.pagamentos.client.KeycloakClient;
import api.pagamentos.documentation.AuthDocumentation;
import api.pagamentos.dto.AuthKeycloakDTO;
import api.pagamentos.dto.form.AuthKeycloak;
import api.pagamentos.dto.form.RefreshKeycloak;
import api.pagamentos.service.impl.AuthServiceImpl;
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