package api.pagamentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pagamentos/status", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PagamentosStatusController {

//    @Autowired
//    StatusServiceImpl service;

    //@PreAuthorize("hasRole('ADMIN')")
//    @GetMapping
//    public ResponseEntity<List<PedidoStatusDTO>> statusAtivoController(
//           @RequestParam("status") StatusPedido statusPedido) {
//        return ResponseEntity.ok(service.statusAtivoService(statusPedido));
//    }
//
//    //@PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> atualizaStatusController(
//            @PathVariable("id") Long id, @RequestParam("status") StatusPedido statusPedido) {
//        service.atualizaStatusService(id, statusPedido);
//        return ResponseEntity.status(204).build();
//    }
}