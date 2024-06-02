package api.pedidos.service;


import api.pedidos.dto.AuthKeycloakDTO;
import api.pedidos.dto.form.AuthKeycloak;
import api.pedidos.dto.form.RefreshKeycloak;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}