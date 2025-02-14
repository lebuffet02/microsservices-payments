package api.pagamentos.service;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.dto.response.PagamentoStatusDTO;

import java.util.List;

public interface StatusService {

    List<PagamentoStatusDTO> buscarStatusService(StatusPedido statusPedido);

    void atualizaStatusService(Long id, StatusPedido statusPedido);
}
