package api.pedidos.service.impl;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import api.pedidos.exception.PedidosException;
import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {

    @Mock
    MapperPedido mapper;
    @Mock
    PedidoRepository repository;
    @InjectMocks
    StatusServiceImpl service;

    @DisplayName("Testa método buscarStatusService.")
    @Test
    void testaBuscarStatusService() {
        when(repository.buscaStatusAtivo(StatusPedido.PROCESSANDO)).thenReturn(getList());
        when(mapper.pedidoEntityToStatusDTO(any())).thenReturn(any());
        Assertions.assertNotNull(service.buscarStatusService(StatusPedido.PROCESSANDO));
    }

    @DisplayName("Lança exceção no método buscarStatusService.")
    @Test
    void lancaExcecaoNoMetodoBuscarStatusService() {
        when(repository.buscaStatusAtivo(StatusPedido.PROCESSANDO)).thenThrow(PedidosException.class);
        assertThrows(PedidosException.class, () -> service.buscarStatusService(StatusPedido.PROCESSANDO));
    }

    @DisplayName("Testa método atualizaStatusService.")
    @Test
    void testaBuscarAtualizaStatusService() {
        when(repository.findById(1L)).thenReturn(Optional.of(getPedidoEntity()));
        service.atualizaStatusService(1L, StatusPedido.PROCESSANDO);
    }

    @DisplayName("Testa vazio no método atualizaStatusService.")
    @Test
    void testaVazioNoMetodoBuscarAtualizaStatusService() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PedidosException.class, () -> service.atualizaStatusService(1L, StatusPedido.PROCESSANDO));
    }

    @DisplayName("Lança exceção no método atualizaStatusService.")
    @Test
    void lancaExcecaoNoMetodoAtualizaStatusService() {
        when(repository.buscaStatusAtivo(StatusPedido.PROCESSANDO)).thenThrow(PedidosException.class);
        assertThrows(PedidosException.class, () -> service.buscarStatusService(StatusPedido.PROCESSANDO));
    }

    @DisplayName("Lança exceção no orElse no método atualizaStatusService.")
    @Test
    void lancaExcecaoNoOrElseNoMetodoAtualizaStatusService() {
        PedidosException exception = assertThrows(PedidosException.class, () -> service.atualizaStatusService(-1L, StatusPedido.RECUSADO));
        assertThat(exception.getMessage()).isEqualTo("Id do pedido não foi encontrado.");
    }

    @DisplayName("Lança exceção genérica no método atualizaStatusService.")
    @Test
    void lancaExcecaoGenericaNoMetodoAtualizaStatusService() {
        when(repository.findById(1L)).thenReturn(Optional.of(getPedidoEntity()));
        doThrow(new RuntimeException("Falha ao atualizar")).when(repository).atualizaStatusPedido(StatusPedido.PROCESSANDO, 1L);
        Assertions.assertThrows(PedidosException.class, () -> service.atualizaStatusService(1L, StatusPedido.PROCESSANDO));
    }

    @DisplayName("Testa método statusByPedidoIdService.")
    @Test
    void testaStatusByPedidoIdService() {
        PedidoEntity pedidoEntity = getPedidoEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(pedidoEntity));
        when(mapper.pedidoEntityToStatusDTO(pedidoEntity)).thenReturn(new PedidoStatusDTO("1", "", "", 1.0, 1.0, StatusPedido.PROCESSANDO));
        service.statusByPedidoIdService(1L);
    }

    @DisplayName("Testa vazio no método statusByPedidoIdService.")
    @Test
    void testaVazioNoMetodoStatusByPedidoIdService() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PedidosException.class, () -> service.statusByPedidoIdService(1L));
    }

    private List<PedidoEntity> getList() {
        List<PedidoEntity> list = new ArrayList<>();
        list.add(getPedidoEntity());
        return list;
    }

    private PedidoEntity getPedidoEntity() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setStatus(StatusPedido.APROVADO);
        return pedidoEntity;
    }
}