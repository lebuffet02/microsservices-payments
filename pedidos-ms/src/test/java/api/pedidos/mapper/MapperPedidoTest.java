package api.pedidos.mapper;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoDTO;
import api.pedidos.entity.PedidoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MapperPedidoTest {

    @InjectMocks
    MapperPedido mapper;

    @DisplayName("Mapper de entidade para status DTO.")
    @Test
    void testaPedidoEntityToStatusDTO() {
        Assertions.assertNotNull(mapper.pedidoEntityToStatusDTO(getPedidoEntity()));
    }

    @DisplayName("Mapper de pedido DTO para eentidade.")
    @Test
    void testaPedidoDTOToEntity() {
        Assertions.assertNotNull(mapper.pedidoDTOToEntity(new PedidoDTO("prduto", "tipo", 2.0, 1.0)));
    }

    private PedidoEntity getPedidoEntity() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(1L);
        pedidoEntity.setStatus(StatusPedido.PROCESSANDO);
        pedidoEntity.setValor(1.0);
        pedidoEntity.setNomeProduto("produto");
        pedidoEntity.setPesoKg(2.0);
        return pedidoEntity;
    }
}