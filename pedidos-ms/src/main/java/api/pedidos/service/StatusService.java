package api.pedidos.service;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoStatusDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StatusService {

    List<PedidoStatusDTO> statusAtivoService(StatusPedido statusPedido);

    void atualizaStatusService(Long id, StatusPedido statusPedido);
}