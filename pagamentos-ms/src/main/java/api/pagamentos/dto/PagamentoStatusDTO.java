package api.pagamentos.dto;

import api.pagamentos.constantes.StatusPagamento;
import lombok.Builder;

@Builder
public record PagamentoStatusDTO(
        String id,
        UsuarioDTO usuarioDTO,
        String cartao,
        int parcelas,
        String nomeProduto,
        String tipo,
        double pesoKg,
        double valor,
        int quantidade,
        String horario,
        StatusPagamento status){}
