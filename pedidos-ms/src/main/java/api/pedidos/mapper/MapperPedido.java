package api.pedidos.mapper;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoDTO;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperPedido {

    public PedidoStatusDTO pedidoEntityToStatusDTO(PedidoEntity pedidoEntity) {
        return PedidoStatusDTO.builder()
                .email(pedidoEntity.getEmail())
                .nome(pedidoEntity.getNomeProduto())
                .tipo(pedidoEntity.getTipo())
                .peso(pedidoEntity.getPeso())
                .status(pedidoEntity.getStatus())
                .build();
    }

    public PedidoEntity pedidoDTOToEntity(PedidoDTO pedidoDTO) {
        return PedidoEntity.builder()
                .email(pedidoDTO.email())
                .nomeProduto(pedidoDTO.nome())
                .tipo(pedidoDTO.tipo())
                .peso(pedidoDTO.peso())
                .status(StatusPedido.PROCESSANDO)
                .build();
    }
}
