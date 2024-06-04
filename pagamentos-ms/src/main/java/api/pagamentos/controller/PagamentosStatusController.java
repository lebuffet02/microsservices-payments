package api.pagamentos.controller;

import api.pagamentos.constantes.StatusPagamento;
import api.pagamentos.documentation.PagamentosStatusDocumentation;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pagamentos/status", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PagamentosStatusController implements PagamentosStatusDocumentation {

    @Autowired
    StatusServiceImpl service;

    @Override
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PagamentoStatusDTO>> statusController(
           @RequestParam("statusPagamento") StatusPagamento statusPagamento) {
        return ResponseEntity.ok(service.buscarStatusService(statusPagamento));
    }

    @Override
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaStatusController(@PathVariable("id") Long id, @RequestParam("statusPagamento") StatusPagamento statusPagamento) {
        if(statusPagamento.equals(StatusPagamento.PAGAMENTO_RECEBIDO) || statusPagamento.equals(StatusPagamento.RECUSADO)) {
            service.atualizaStatusService(id, statusPagamento);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}