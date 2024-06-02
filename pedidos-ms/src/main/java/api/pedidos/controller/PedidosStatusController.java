package api.pedidos.controller;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pedidos/status", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PedidosStatusController {

    @Autowired
    StatusServiceImpl service;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PedidoStatusDTO>> statusAtivoController(
           @RequestParam("status") StatusPedido statusPedido) {
        return ResponseEntity.ok(service.buscarStatusService(statusPedido));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaStatusController(
            @PathVariable("id") Long id, @RequestParam("status") StatusPedido statusPedido) {
        service.atualizaStatusService(id, statusPedido);
        return ResponseEntity.status(204).build();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("email")
    public ResponseEntity<Optional<PedidoStatusDTO>> statusByEmailController(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.statusByEmailService(email));
    }
}