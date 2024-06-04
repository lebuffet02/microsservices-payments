package api.pagamentos.client;

import api.pagamentos.dto.PedidoStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;


@FeignClient(name = "pedidos-ms", url = "${pedidos.port}", path = "/api/pedidos/status")
public interface PedidosClient {

    @GetMapping("/email")
    Optional<PedidoStatusDTO> getStatusEmailPedido(@RequestParam("pedidoId") Long pedidoId);

}
