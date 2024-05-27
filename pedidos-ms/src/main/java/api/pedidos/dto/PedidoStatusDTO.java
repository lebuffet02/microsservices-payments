package api.pedidos.dto;

import api.pedidos.constantes.StatusPedido;
import lombok.Builder;

@Builder
public record PedidoStatusDTO(String nome, String tipo, int peso, StatusPedido status){}
