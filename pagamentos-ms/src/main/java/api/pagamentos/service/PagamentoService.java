package api.pagamentos.service;

import api.pagamentos.dto.PagamentoStatusDTO;
import org.springframework.data.domain.Page;

public interface PagamentoService {

    Page<PagamentoStatusDTO> getAllService(int page, int size);
}
