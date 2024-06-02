package api.pedidos.service.impl;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;

import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import api.pedidos.service.StatusService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    MapperPedido mapper;
    @Autowired
    PedidoRepository repository;

    @Override
    public List<PedidoStatusDTO> buscarStatusService(StatusPedido statusPedido) {
        try {
            return repository.buscaStatusAtivo(statusPedido)
                    .stream()
                    .map(p -> mapper.pedidoEntityToStatusDTO(p))
                    .collect(Collectors.toList());
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar status.");
        }
    }

    @Transactional
    @Override
    public void atualizaStatusService(Long id, StatusPedido statusPedido) {
        try {
            PedidoEntity pedidoEntity = repository.findById(id)
                    .orElseThrow(() -> new PedidosException(ResponseEnum.ERRO_INTERNO, "Id do pedido não foi encontrado."));
            if(!pedidoEntity.getStatus().equals(statusPedido)) {
                repository.atualizaStatusPedido(statusPedido, id);
            }
        } catch (RuntimeException | Error e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, !e.getMessage().toLowerCase().contains("id")
                            ? "Falha ao atualizar o status do pedido." : e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<PedidoStatusDTO> statusByEmailService(String email) {
        try {
            PedidoEntity pedidoEntity = repository.findByEmail(email)
                    .orElseThrow(() -> new PedidosException(ResponseEnum.ERRO_INTERNO, "Usuário não foi encontrado."));
            return Optional.of(mapper.pedidoEntityToStatusDTO(pedidoEntity));

        } catch (RuntimeException | Error e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, !e.getMessage().toLowerCase().contains("usuário")
                    ? "Falha ao obter status do usuário." : e.getMessage());
        }
    }
}