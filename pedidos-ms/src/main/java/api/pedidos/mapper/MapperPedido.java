package api.pedidos.mapper;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.response.PedidoDTO;
import api.pedidos.dto.response.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperPedido {

    public PedidoStatusDTO pedidoEntityToStatusDTO(PedidoEntity pedidoEntity) {
        return PedidoStatusDTO.builder()
                .id(pedidoEntity.getId().toString())
                .nomeProduto(pedidoEntity.getNomeProduto())
                .tipo(pedidoEntity.getTipo())
                .pesoKg(pedidoEntity.getPesoKg())
                .valor(pedidoEntity.getValor())
                .status(pedidoEntity.getStatus())
                .build();
    }

    public PedidoEntity pedidoDTOToEntity(PedidoDTO pedidoDTO) {
        return PedidoEntity.builder()
                .nomeProduto(pedidoDTO.nomeProduto())
                .tipo(pedidoDTO.tipo())
                .pesoKg(pedidoDTO.pesoKg())
                .valor(pedidoDTO.valor())
                .status(StatusPedido.PROCESSANDO)
                .build();
    }
}