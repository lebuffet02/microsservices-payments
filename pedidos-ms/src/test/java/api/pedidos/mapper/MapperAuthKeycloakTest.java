package api.pedidos.mapper;

import api.pedidos.dto.form.AuthKeycloak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MapperAuthKeycloakTest {

    @InjectMocks
    MapperAuthKeycloak mapper;

    @DisplayName("Deve transformar o objeto AuthKeycloak no AuthKeycloakDTO.")
    @Test
    void testaMapperBuilderToAuthKeycloakDTO() {
        Assertions.assertNotNull(mapper.builderToAuthKeycloakDTO(getAuthKeycloak()));
    }

    private AuthKeycloak getAuthKeycloak() {
        return new AuthKeycloak("passwd", "client", "grant", "user","secret","token",
                300, 300, "refresh", "type", 2, "state", "scope");
    }
}