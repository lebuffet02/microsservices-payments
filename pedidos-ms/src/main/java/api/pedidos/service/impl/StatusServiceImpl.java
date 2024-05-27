package api.pedidos.service.impl;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoDTO;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import api.pedidos.service.StatusService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    MapperPedido mapper;
    @Autowired
    PedidoRepository repository;


    @Override
    public List<PedidoStatusDTO> statusAtivoService(StatusPedido statusPedido) {
        try {
            return repository.buscaStatusAtivo(statusPedido)
                    .stream()
                    .map(p -> mapper.pedidoEntityToStatusDTO(p))
                    .collect(Collectors.toList());
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar status aprovado.");
        }
    }

    @Transactional
    @Override
    public void atualizaStatusService(Long id, StatusPedido statusPedido) {
        try {
            PedidoEntity pedidoEntityId = repository.findById(id).orElseThrow(() -> new PedidosException(ResponseEnum.ERRO_INTERNO, "Id do pedido não foi encontrado."));
            if(!pedidoEntityId.getStatus().equals(statusPedido)) {
                repository.atualizaStatusPedido(statusPedido, id);
            }
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO,
                    !e.getMessage().toLowerCase().contains("id") ? "Falha ao atualizar o status do pedido." : e.getMessage());
        }
    }
}