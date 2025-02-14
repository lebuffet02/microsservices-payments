package api.pagamentos.service;

import api.pagamentos.dto.response.PagamentoStatusDTO;
import api.pagamentos.dto.request.PagamentoRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PagamentoService {

    Page<PagamentoStatusDTO> getAllService(int page, int size);

    Optional<PagamentoStatusDTO> saveService(PagamentoRequest pagamentoRequest, Long pedidoId);
}
