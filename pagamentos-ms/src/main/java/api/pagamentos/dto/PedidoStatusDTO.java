package api.pagamentos.dto;

import api.pagamentos.constantes.StatusPagamento;
import lombok.Builder;

@Builder
public record PedidoStatusDTO(
        String id,
        String email,
        String nomeProduto,
        String tipo,
        double pesoKg,
        double valor,
        StatusPagamento status){}
