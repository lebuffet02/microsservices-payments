package api.pedidos.service.impl;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.response.PedidoDTO;
import api.pedidos.dto.response.PedidoStatusDTO;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    PedidoRepository repository;
    @Mock
    MapperPedido mapper;
    @InjectMocks
    PedidoServiceImpl service;


    @DisplayName("Testa método getAllService.")
    @Test
    void testaGetAllService() {
        PedidoEntity pedidoEntity = getPedidoEntity();
        when(repository.findAll(PageRequest.of(1, 1, Sort.by("valor").ascending()))).thenReturn(new PageImpl<>(List.of(pedidoEntity)));
        when(mapper.pedidoEntityToStatusDTO(getPedidoEntity())).thenReturn(new PedidoStatusDTO("1", "", "", 1.0, 1.0, StatusPedido.PROCESSANDO));
        Assertions.assertNotNull(service.getAllService(1, 1));
    }

    @DisplayName("Testa exceção do método getAllService.")
    @Test
    void testaExcecaoPedidosExceptionNoMetodoGetAllService() {
        when(repository.findAll()).thenThrow(PedidosException.class);
        Assertions.assertThrows(PedidosException.class, () -> service.getAllService(1, 1));
    }

    @DisplayName("Testa método saveService.")
    @Test
    void testaSaveService() {
        PedidoDTO pedidoDTO = getPedidoDTO();
        PedidoEntity pedidoEntity = getPedidoEntity();
        when(mapper.pedidoDTOToEntity(pedidoDTO)).thenReturn(pedidoEntity);
        when(mapper.pedidoEntityToStatusDTO(pedidoEntity)).thenReturn(new PedidoStatusDTO("1", "", "", 1.0, 1.0, StatusPedido.PROCESSANDO));
        Assertions.assertNotNull(service.saveService(getPedidoDTO()));
    }

    @DisplayName("Testa vazio no método saveService.")
    @Test
    void testaVazioNoMetodoSaveService() {
        Assertions.assertEquals(Optional.empty(), service.saveService(null));
    }

    @DisplayName("Testa exceção do método saveService.")
    @Test
    void testaExcecaoPedidosExceptionNoMetodoSaveService() {
        when(mapper.pedidoDTOToEntity(any())).thenThrow(PedidosException.class);
       Assertions.assertThrows(PedidosException.class, () -> service.saveService(getPedidoDTO()));
    }


    @DisplayName("Testa método updateService.")
    @Test
    void testaUpdateService() {
        PedidoEntity pedidoEntity = getPedidoEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(getPedidoEntity()));
        when(mapper.pedidoDTOToEntity(getPedidoDTO())).thenReturn(pedidoEntity);
        when(mapper.pedidoEntityToStatusDTO(pedidoEntity)).thenReturn(new PedidoStatusDTO("1", "", "", 1.0, 1.0, StatusPedido.PROCESSANDO));
        Assertions.assertNotNull(service.updateService(1L, getPedidoDTO()));
    }

    @DisplayName("Testa vazio no método updateService.")
    @Test
    void testaVazioNoMetodoUpdateService() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertEquals(Optional.empty(), service.updateService(anyLong(), null));
    }

    @DisplayName("Testa método getByIdService.")
    @Test
    void testaGetByIdService() {
        when(repository.findById(1L)).thenReturn(Optional.of(getPedidoEntity()));
        when(mapper.pedidoEntityToStatusDTO(any())).thenReturn(any());
        Assertions.assertNotNull(service.getByIdService(1L));
    }

    @DisplayName("Testa exceção do método getByIdService.")
    @Test
    void testaExcecaoPedidosExceptionNoMetodoGetByIdService() {
        when(repository.findById(1L)).thenThrow(PedidosException.class);
        Assertions.assertThrows(PedidosException.class, () -> service.getByIdService(-1L));
    }

    @DisplayName("Testa método deleteByIdService.")
    @Test
    void testaDeleteByIdService() {
        service.deleteByIdService(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @DisplayName("Testa exceção do método deleteByIdService.")
    @Test
    void testaExcecaoPedidosExceptionNoMetodoDeleteByIdService() {
        doThrow(PedidosException.class).when(repository).deleteById(-1L);
        Assertions.assertThrows(PedidosException.class, () -> service.deleteByIdService(-1L));
    }

    private PedidoDTO getPedidoDTO() {
        return new PedidoDTO("nome", "tipo", 2.0, 41.99);
    }

    private PedidoEntity getPedidoEntity() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(2L);
        return pedidoEntity;
    }
}