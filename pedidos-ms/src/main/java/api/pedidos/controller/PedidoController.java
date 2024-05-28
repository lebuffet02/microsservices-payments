package api.pedidos.controller;

import api.pedidos.dto.form.PedidoDTO;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.service.impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pedidos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PedidoController {

    @Autowired
    PedidoServiceImpl service;

    //@PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<?> getAllController(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllService(page, size));
    }

    //@PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Optional<PedidoStatusDTO>> saveController(@RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(service.saveService(dto));
    }

    //@PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<Optional<PedidoStatusDTO>> updateController(@PathVariable("id") Long id, @RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(service.updateService(id, dto));
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PedidoStatusDTO>> getByIdController(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getByIdService(id));
    }

    //@PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdController(
            @PathVariable("id") Long id) {
        service.deleteByIdService(id);
        return ResponseEntity.ok().build();
    }
}