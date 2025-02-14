package api.pagamentos.dto.request;

import api.pagamentos.dto.response.UsuarioDTO;
import lombok.Builder;

@Builder
public record PagamentoRequest(UsuarioDTO usuarioDTO, int quantidade, String cartao, int parcelas) {
}
