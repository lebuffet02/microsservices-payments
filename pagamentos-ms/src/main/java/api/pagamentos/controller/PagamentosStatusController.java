package api.pagamentos.controller;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.documentation.PagamentosStatusDocumentation;
import api.pagamentos.dto.response.PagamentoStatusDTO;
import api.pagamentos.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pagamentos/status", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PagamentosStatusController implements PagamentosStatusDocumentation {

    @Autowired
    StatusServiceImpl service;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PagamentoStatusDTO>> statusController(
           @RequestParam("statusPedido") StatusPedido statusPedido) {
        return ResponseEntity.ok(service.buscarStatusService(statusPedido));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaStatusController(@PathVariable("id") Long id, @RequestParam("statusPedido") StatusPedido statusPedido) {
        service.atualizaStatusService(id, statusPedido);
        return ResponseEntity.noContent().build();
    }
}