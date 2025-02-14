package api.pagamentos.service;


import api.pagamentos.dto.response.AuthKeycloakDTO;
import api.pagamentos.dto.request.AuthKeycloak;
import api.pagamentos.dto.request.RefreshKeycloak;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}