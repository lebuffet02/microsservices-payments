package api.pagamentos.dto.response;

import api.pagamentos.constantes.StatusPedido;
import lombok.Builder;

@Builder
public record PedidoStatusDTO(String id, String nomeProduto, String tipo, double pesoKg, double valor, StatusPedido status){}
