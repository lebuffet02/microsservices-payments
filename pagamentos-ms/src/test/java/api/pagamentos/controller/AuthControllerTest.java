package api.pagamentos.controller;

import api.pagamentos.dto.form.AuthKeycloak;
import api.pagamentos.dto.form.RefreshKeycloak;
import api.pagamentos.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    MockMvc mockMvc;
    @Mock
    AuthServiceImpl serviceImpl;
    @InjectMocks
    AuthController controller;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AuthController()).build();
    }

    @DisplayName("Testa chamada ao método tokenService.")
    @Test
    void testaToken() {
        controller.token(any(AuthKeycloak.class));
        verify(serviceImpl, times(1)).tokenService(any());
    }

    @DisplayName("Testa chamada ao método tokenService.")
    @Test
    void testaRefresh() {
        controller.refreshToken(any(RefreshKeycloak.class));
        verify(serviceImpl, times(1)).refreshTokenService(any());
    }

    @DisplayName("Status code 400 pois os dados não foram enviados para a API do keycloak")
    @Test
    void deveDevolverStatus400NoToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/token"))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Status code 404 pois não existe o /refresh, mas sim o /refresh-token")
    @Test
    void deveDevolverStatus404NoRefresh() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/refresh"))
                .andExpect(status().is(404));
    }
}