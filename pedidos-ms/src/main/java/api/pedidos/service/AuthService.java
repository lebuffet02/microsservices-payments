package api.pedidos.service;


import api.pedidos.dto.auth.AuthKeycloak;
import api.pedidos.dto.auth.AuthKeycloakDTO;
import api.pedidos.dto.auth.RefreshKeycloak;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}