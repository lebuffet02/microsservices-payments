package api.pedidos.service.impl;

import api.pedidos.client.KeycloakClient;
import api.pedidos.dto.response.AuthKeycloakDTO;
import api.pedidos.dto.request.AuthKeycloak;
import api.pedidos.dto.request.RefreshKeycloak;
import api.pedidos.exception.AuthException;
import api.pedidos.exception.RefreshException;
import api.pedidos.mapper.MapperAuthKeycloak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    KeycloakClient client;
    @Mock
    MapperAuthKeycloak mapper;
    @InjectMocks
    AuthServiceImpl serviceImpl;


    @DisplayName("Deve retornar com sucesso o retorno do objeto ao chamar o token na API do keycloak.")
    @Test
    void deveRetornarComSucessoAoChamarTokenNaApiDoKeycloak() {
        when(client.getDadosKeycloak(any(Object.class))).thenReturn(getAuthKeycloak());
        when(mapper.builderToAuthKeycloakDTO(any())).thenReturn(getAuthApiKeycloakDTO());
        Assertions.assertNotNull(serviceImpl.tokenService(getAuthKeycloak()));
    }

    @DisplayName("Deve lançar exception do tipo NullPointer ao não conseguir coletar os dados do token na API do Keycloak.")
    @Test
    void lancaExceptionDoTipoAuthExceptionNoTokenService() {
        when(client.getDadosKeycloak(any())).thenThrow(NullPointerException.class);
        Assertions.assertThrows(AuthException.class, () -> serviceImpl.tokenService(getAuthKeycloak()));
    }

    @DisplayName("Deve retornar com sucesso o retorno do objeto ao chamar o refresh token na API do keycloak.")
    @Test
    void deveRetornarComSucessoAoChamarRefreshTokenNaApiDoKeycloak() {
        when(client.getDadosKeycloak(any(Object.class))).thenReturn(getAuthKeycloak());
        when(mapper.builderToAuthKeycloakDTO(any())).thenReturn(getAuthApiKeycloakDTO());
        Assertions.assertNotNull(serviceImpl.refreshTokenService(new RefreshKeycloak("client", "refresh")));
    }

    @DisplayName("Deve lançar exception do tipo NullPointer ao não conseguir coletar os dados do refresh na API do Keycloak.")
    @Test
    void lancaExceptionDoTipoAuthExceptionNoRefreshTokenService() {
        when(client.getDadosKeycloak(any())).thenThrow(NullPointerException.class);
        Assertions.assertThrows(RefreshException.class, () -> serviceImpl.refreshTokenService(new RefreshKeycloak("client", "refresh")));
    }


    private AuthKeycloak getAuthKeycloak() {
        return new AuthKeycloak("passwd", "client", "grant", "user","secret","token",
                300, 300, "refresh", "type", 2, "state", "scope");
    }

    private AuthKeycloakDTO getAuthApiKeycloakDTO() {
        return new AuthKeycloakDTO("token", 300, 300, "refresh",
                "type", 12, "state", "scope");
    }
}