package api.pedidos.mapper;

import api.pedidos.dto.PedidoDTO;
import api.pedidos.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperPedido {

    public PedidoDTO pedidoEntityToDTO(PedidoEntity pedidoEntity) {
        return PedidoDTO.builder()
                .nome(pedidoEntity.getNome())
                .tipo(pedidoEntity.getTipo())
                .peso(pedidoEntity.getPeso())
                .status(pedidoEntity.getStatus())
                .build();
    }

    public PedidoEntity pedidoDTOToEntity(PedidoDTO pedidoDTO) {
        return PedidoEntity.builder()
                .nome(pedidoDTO.nome())
                .tipo(pedidoDTO.tipo())
                .status(pedidoDTO.status())
                .peso(pedidoDTO.peso())
                .build();
    }
}
