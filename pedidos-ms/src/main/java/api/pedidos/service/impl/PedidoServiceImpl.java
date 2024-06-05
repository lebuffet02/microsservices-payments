package api.pedidos.service.impl;

import api.pedidos.dto.PedidoDTO;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import api.pedidos.service.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository repository;
    @Autowired
    MapperPedido mapper;

    @Override
    public Page<PedidoStatusDTO> getAllService(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("valor").ascending());
            return repository.findAll(pageable)
                    .map(mapper::pedidoEntityToStatusDTO);
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar todos.");
        }
    }

    @Override
    public Optional<PedidoStatusDTO> saveService(PedidoDTO pedidoDTO) {
        return getPedidoDTO(null, pedidoDTO, "Falha ao cadastrar pedido.");
    }

    @Transactional
    public Optional<PedidoStatusDTO> updateService(Long id, PedidoDTO pedidoDTO) {
        return (repository.findById(id).isPresent() ? getPedidoDTO(id, pedidoDTO, "Falha ao atualizar pedido.") : Optional.empty());
    }

    @Override
    @Transactional
    public Optional<PedidoStatusDTO> getByIdService(Long id) {
        try {
            Optional<PedidoEntity> pedidoEntity = repository.findById(id);
            return pedidoEntity.map(p -> mapper.pedidoEntityToStatusDTO(p));
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar usuário pelo id.");
        }
    }

    @Override
    @Transactional
    public void deleteByIdService(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, "Falha ao deletar usuário pelo id.");
        }
    }

    private Optional<PedidoStatusDTO> getPedidoDTO(Long id, PedidoDTO pedidoDTO, String mensagemError) {
        try {
            if(!ObjectUtils.isEmpty(pedidoDTO)) {
                PedidoEntity pedidoEntity = mapper.pedidoDTOToEntity(pedidoDTO);
                pedidoEntity.setId(id);
                repository.save(pedidoEntity);
                return Optional.of(mapper.pedidoEntityToStatusDTO(pedidoEntity));
            }
            return Optional.empty();
        } catch (RuntimeException | Error e) {
            throw new PedidosException(ResponseEnum.ERRO_INTERNO, mensagemError);
        }
    }
}