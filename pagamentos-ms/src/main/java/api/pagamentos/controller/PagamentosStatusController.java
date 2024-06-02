package api.pagamentos.controller;

import api.pagamentos.constantes.StatusPagamento;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pagamentos/status", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PagamentosStatusController {

    @Autowired
    StatusServiceImpl service;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PagamentoStatusDTO>> statusController(
           @RequestParam("status") StatusPagamento statusPagamento) {
        return ResponseEntity.ok(service.buscarStatusService(statusPagamento));
    }
//
//    //@PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> atualizaStatusController(
//            @PathVariable("id") Long id, @RequestParam("status") StatusPedido statusPedido) {
//        service.atualizaStatusService(id, statusPedido);
//        return ResponseEntity.status(204).build();
//    }
}