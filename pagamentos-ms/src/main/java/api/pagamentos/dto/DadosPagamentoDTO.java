package api.pagamentos.dto;

import api.pagamentos.constantes.StatusPagamento;
import lombok.Builder;

@Builder
public record DadosPagamentoDTO(
        UsuarioDTO usuarioDTO,
        String cartao,
        int parcelas,
        String nomeProduto,
        String tipo,
        double pesoKg,
        double valor,
        StatusPagamento status){}