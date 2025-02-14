package api.pedidos.dto.response;

import lombok.Builder;

@Builder
public record PedidoDTO(String nomeProduto, String tipo, double pesoKg, double valor){}
