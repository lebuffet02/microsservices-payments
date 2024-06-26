package api.pedidos.controller;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.documentation.PedidoStatusDocumentation;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pedidos/status", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PedidosStatusController implements PedidoStatusDocumentation {

    @Autowired
    StatusServiceImpl service;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PedidoStatusDTO>> statusPedidoController(
           @RequestParam("status") StatusPedido statusPedido) {
        return ResponseEntity.ok(service.buscarStatusService(statusPedido));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaStatusController(
            @PathVariable("id") Long id, @RequestParam("status") StatusPedido statusPedido) {
        service.atualizaStatusService(id, statusPedido);
        return ResponseEntity.status(204).build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pedido")
    public ResponseEntity<Optional<PedidoStatusDTO>> statusByIdController(@RequestParam("pedidoId") Long pedidoId) {
        return ResponseEntity.ok(service.statusByPedidoIdService(pedidoId));
    }
}