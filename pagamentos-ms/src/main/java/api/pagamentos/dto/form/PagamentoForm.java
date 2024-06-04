package api.pagamentos.dto.form;

import api.pagamentos.dto.UsuarioDTO;

public record PagamentoForm(
        UsuarioDTO usuarioDTO,
        int quantidade,
        String cartao,
        int parcelas
) {
}
