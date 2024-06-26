package api.pagamentos.controller;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.service.impl.PagamentoServiceImpl;
import api.pagamentos.service.impl.StatusServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PagamentosStatusControllerTest {

    @Mock
    StatusServiceImpl service;
    @InjectMocks
    PagamentosStatusController controller;

    @DisplayName("Testa método statusController.")
    @Test
    void testaStatusController() {
        controller.statusController(StatusPedido.RECUSADO);
        verify(service, times(1)).buscarStatusService(StatusPedido.RECUSADO);
    }

    @DisplayName("Testa método atualizaStatusController.")
    @Test
    void testaAtualizaStatusController() {
        controller.atualizaStatusController(anyLong(), any());
        verify(service, times(1)).atualizaStatusService(anyLong(), any());
    }
}