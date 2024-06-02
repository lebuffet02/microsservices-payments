package api.pagamentos.controller;

import api.pagamentos.documentation.PagamentoDocumentation;
import api.pagamentos.dto.PagamentoDTO;
import api.pagamentos.service.impl.PagamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pagamentos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PagamentoController implements PagamentoDocumentation {

    @Autowired
    PagamentoServiceImpl service;

    @Override
    //@PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<?> getAllController(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllService(page, size));
    }

    @Override
    //@PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> saveController(
            @RequestBody() PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok(service.saveService(pagamentoDTO));
    }
}