package api.pagamentos.controller;

import api.pagamentos.service.impl.PagamentoServiceImpl;
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
class PagamentoControllerTest {

    @Mock
    PagamentoServiceImpl service;
    @InjectMocks
    PagamentoController controller;

    @DisplayName("Testa método getAllController.")
    @Test
    void testaGetAllController() {
        controller.getAllController(anyInt(), anyInt());
        verify(service, times(1)).getAllService(anyInt(), anyInt());
    }

    @DisplayName("Testa método saveController.")
    @Test
    void testaSaveController() {
        controller.saveController(any(), anyLong());
        verify(service, times(1)).saveService(any(), anyLong());
    }
}