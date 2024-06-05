package api.pedidos.dto;

import lombok.Builder;

@Builder
public record PedidoDTO(
        String nomeProduto,
        String tipo,
        double pesoKg,
        double valor){}
