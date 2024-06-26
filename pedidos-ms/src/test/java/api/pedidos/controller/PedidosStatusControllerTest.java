package api.pedidos.controller;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.service.impl.StatusServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PedidosStatusControllerTest {

    @Mock
    StatusServiceImpl serviceImpl;
    @InjectMocks
    PedidosStatusController controller;

    @DisplayName("Testa método buscar pedidos por status.")
    @Test
    void testaStatusPedidoController() {
        controller.statusPedidoController(StatusPedido.RECUSADO);
        verify(serviceImpl, times(1)).buscarStatusService(StatusPedido.RECUSADO);
    }

    @DisplayName("Testa método atualizar status.")
    @Test
    void testaAtualizaStatusController() {
        controller.atualizaStatusController(anyLong(), any());
        verify(serviceImpl, times(1)).atualizaStatusService(anyLong(), any());
    }

    @DisplayName("Testa método buscar status através do ID.")
    @Test
    void testaStatusByIdController() {
        controller.statusByIdController(anyLong());
        verify(serviceImpl, times(1)).statusByPedidoIdService(anyLong());
    }
}