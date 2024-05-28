package api.pagamentos.dto;

import api.pagamentos.constantes.StatusPagamento;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record PagamentoStatusDTO(
        UsuarioDTO usuarioDTO,
        String email,
        String nome,
        String tipo,
        int peso,
        LocalDateTime horario,
        StatusPagamento status){}
