package api.pagamentos.service;


import api.pagamentos.dto.AuthKeycloakDTO;
import api.pagamentos.dto.form.AuthKeycloak;
import api.pagamentos.dto.form.RefreshKeycloak;

public interface AuthService {

    AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak);

    AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh);
}