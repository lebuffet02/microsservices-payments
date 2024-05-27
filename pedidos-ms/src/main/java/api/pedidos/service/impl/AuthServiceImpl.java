package api.pedidos.service.impl;

import api.pedidos.client.KeycloakClient;
import api.pedidos.dto.auth.AuthKeycloak;
import api.pedidos.dto.auth.AuthKeycloakDTO;
import api.pedidos.dto.auth.RefreshKeycloak;
import api.pedidos.exception.AuthException;
import api.pedidos.exception.RefreshException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.mapper.MapperAuthKeycloak;
import api.pedidos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    KeycloakClient client;
    @Autowired
    MapperAuthKeycloak mapper;


    @Override
    public AuthKeycloakDTO tokenService(AuthKeycloak authKeycloak) {
        try {
            return mapper.builderToAuthKeycloakDTO(getChamadaFeign(authKeycloak));
        } catch (RuntimeException ex) {
            throw new AuthException(ResponseEnum.ERRO_EXTERNO, "Error when validating token");
        }
    }

    @Override
    public AuthKeycloakDTO refreshTokenService(RefreshKeycloak refresh) {
        try {
            return mapper.builderToAuthKeycloakDTO(getChamadaFeign(refresh));
        } catch (RuntimeException ex) {
            throw new RefreshException(ResponseEnum.ERRO_EXTERNO, "Error Refresh token");
        }
    }

    private AuthKeycloak getChamadaFeign(Object obj) {
        if (obj instanceof AuthKeycloak authKeycloak) {
            AuthKeycloak auth = client.getDadosKeycloak(getFormTokenData(authKeycloak));
            Objects.requireNonNull(auth);
            return auth;
        }
        RefreshKeycloak refKeycloak = (RefreshKeycloak) obj;
        AuthKeycloak refresh = client.getDadosKeycloak(getFormRefreshData(refKeycloak));
        Objects.requireNonNull(refresh);
        return refresh;
    }

    private MultiValueMap<String, String> getFormTokenData(AuthKeycloak auth) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", auth.clientId());
        formData.add("username", auth.username());
        formData.add("password", auth.password());
        formData.add("grant_type", auth.grantType());
        return formData;
    }

    private MultiValueMap<String, String> getFormRefreshData(RefreshKeycloak refresh) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", refresh.clientId());
        formData.add("refresh_token", refresh.refresh_token());
        formData.add("grant_type", "refresh_token");
        return formData;
    }
}