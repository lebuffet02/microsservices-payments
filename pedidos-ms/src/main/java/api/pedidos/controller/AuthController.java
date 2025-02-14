package api.pedidos.controller;

import api.pedidos.documentation.AuthDocumentation;
import api.pedidos.dto.response.AuthKeycloakDTO;
import api.pedidos.dto.request.AuthKeycloak;
import api.pedidos.dto.request.RefreshKeycloak;
import api.pedidos.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthDocumentation {

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