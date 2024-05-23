package api.pedidos.controller;

import api.pedidos.dto.PedidoDTO;
import api.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pedidos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PedidoController {

    @Autowired
    PedidoService service;

    @GetMapping
    public ResponseEntity<?> getAllController() {
        return ResponseEntity.ok(service.getAllService());
    }

    @PostMapping
    public ResponseEntity<Optional<PedidoDTO>> saveController(@RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(service.saveService(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<PedidoDTO>> updateController(@PathVariable("id") Long id, @RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(service.updateService(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PedidoDTO>> getByIdController(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getByIdService(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdController(
            @PathVariable("id") Long id) {
        service.deleteByIdService(id);
        return ResponseEntity.ok().build();
    }
}