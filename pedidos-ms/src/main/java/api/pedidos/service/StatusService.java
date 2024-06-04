package api.pedidos.service;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoStatusDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface StatusService {

    List<PedidoStatusDTO> buscarStatusService(StatusPedido statusPedido);

    void atualizaStatusService(Long id, StatusPedido statusPedido);

    Optional<PedidoStatusDTO> statusByPedidoIdService(Long pedidoId);
}