package api.pedidos.service;

import api.pedidos.dto.response.PedidoDTO;
import api.pedidos.dto.response.PedidoStatusDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PedidoService {

    Page<PedidoStatusDTO> getAllService(int page, int size);

    Optional<PedidoStatusDTO> saveService(PedidoDTO pedidoDTO);

    Optional<PedidoStatusDTO> updateService(Long id, PedidoDTO pedidoDTO);

    Optional<PedidoStatusDTO> getByIdService(Long id);

    void deleteByIdService(Long id);
}