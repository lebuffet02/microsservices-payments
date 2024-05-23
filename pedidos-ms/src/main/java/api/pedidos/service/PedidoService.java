package api.pedidos.service;

import api.pedidos.dto.PedidoDTO;
import api.pedidos.entity.PedidoEntity;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;
    @Autowired
    MapperPedido mapper;

    public List<PedidoDTO> getAllService() {
        try {
            return repository.findAll().stream()
                    .map(mapper::pedidoEntityToDTO)
                    .collect(Collectors.toList());
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar todos.");
        }
    }

    public Optional<PedidoDTO> saveService(PedidoDTO pedidoDTO) {
        return getPedidoDTO(pedidoDTO, "Falha ao cadastrar pedido.");
    }

    public Optional<PedidoDTO> updateService(Long id, PedidoDTO pedidoDTO) {
        return repository.findById(id).isPresent() ? getPedidoDTO(pedidoDTO, "Falha ao atualizar pedido.") : Optional.empty();
    }

    public Optional<PedidoDTO> getByIdService(Long id) {
        try {
            Optional<PedidoEntity> pedidoEntity = repository.findById(id);
            return pedidoEntity.map(p -> mapper.pedidoEntityToDTO(p));
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar usuário pelo id.");
        }
    }

    public void deleteByIdService(Long id) {
        try {
           repository.deleteById(id);
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao deletar usuário pelo id.");
        }
    }

    private Optional<PedidoDTO> getPedidoDTO(PedidoDTO pedidoDTO, String mensagemError) {
        try {
            if(!ObjectUtils.isEmpty(pedidoDTO)) {
                PedidoEntity pedidoEntity = repository.save(mapper.pedidoDTOToEntity(pedidoDTO));
                return Optional.of(mapper.pedidoEntityToDTO(pedidoEntity));
            }
            return Optional.empty();
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, mensagemError);
        }
    }
}