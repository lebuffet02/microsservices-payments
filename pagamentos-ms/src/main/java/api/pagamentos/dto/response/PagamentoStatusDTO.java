package api.pagamentos.dto.response;

import api.pagamentos.constantes.StatusPedido;
import lombok.Builder;

@Builder
public record PagamentoStatusDTO(String id, UsuarioDTO usuarioDTO, String cartao, int parcelas, String nomeProduto, String tipo, double pesoKg, double valor, int quantidade, String horario, StatusPedido status, boolean isPagamentoAceito){}
