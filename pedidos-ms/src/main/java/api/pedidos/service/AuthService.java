package api.pedidos.service;


import api.pedidos.dto.response.AuthKeycloakDTO;
import api.pedidos.dto.request.AuthKeycloak;
import api.pedidos.dto.request.RefreshKeycloak;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}