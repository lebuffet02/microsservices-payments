package api.pagamentos.client;

import api.pagamentos.dto.PedidoStatusUsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pedidos-ms", url = "${pedidos.port")
public interface PedidosClient {

    @GetMapping("api/pedidos/status/email")
    PedidoStatusUsuarioDTO getStatusEmailPedido(@RequestParam("email") String email);
}
