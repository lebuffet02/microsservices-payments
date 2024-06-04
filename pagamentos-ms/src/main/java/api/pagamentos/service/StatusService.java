package api.pagamentos.service;

import api.pagamentos.constantes.StatusPagamento;
import api.pagamentos.dto.PagamentoStatusDTO;

import java.util.List;

public interface StatusService {

    List<PagamentoStatusDTO> buscarStatusService(StatusPagamento statusPagamento);

    void atualizaStatusService(Long id, StatusPagamento statusPagamento);
}
