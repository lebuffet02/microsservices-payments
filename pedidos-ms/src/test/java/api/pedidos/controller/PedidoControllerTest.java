package api.pedidos.controller;

import api.pedidos.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    PedidoServiceImpl service;
    @InjectMocks
    PedidoController controller;

    @DisplayName("Testa método de listar todos os pedidos.")
    @Test
    void testaGetAllController() {
        controller.getAllController(anyInt(), anyInt());
        verify(service, times(1)).getAllService(anyInt(), anyInt());
    }

    @DisplayName("Testa método salvar usuário.")
    @Test
    void testaSaveController() {
        controller.saveController(any());
        verify(service, times(1)).saveService(any());
    }

    @DisplayName("Testa método atualizar usuário.")
    @Test
    void testaUpdateController() {
        controller.updateController(anyLong(), any());
        verify(service, times(1)).updateService(anyLong(), any());
    }

    @DisplayName("Testa método de pegar um usuário.")
    @Test
    void testaGetByIdController() {
        controller.getByIdController(anyLong());
        verify(service, times(1)).getByIdService(anyLong());
    }

    @DisplayName("Testa método ddeletar usuário.")
    @Test
    void testaDeleteByIdController() {
        controller.deleteByIdController(anyLong());
        verify(service, times(1)).deleteByIdService(anyLong());
    }
}