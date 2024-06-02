package api.pagamentos.dto;

import api.pagamentos.constantes.StatusPagamento;
import lombok.Builder;

@Builder
public record PedidoStatusUsuarioDTO(
        String email,
        String nomeProduto,
        String tipo,
        double pesoKg,
        double valor,
        StatusPagamento statusPagamento){}
