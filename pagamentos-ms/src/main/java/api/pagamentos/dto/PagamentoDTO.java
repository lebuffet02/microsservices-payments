package api.pagamentos.dto;

import lombok.Builder;

@Builder
public record PagamentoDTO(
        UsuarioDTO usuarioDTO,
        String nomeProduto,
        String tipo,
        double pesoKg,
        int quantidade,
        double valor){}