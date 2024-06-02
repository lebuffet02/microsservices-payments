package api.pagamentos.service.impl;

import api.pagamentos.client.PedidosClient;
import api.pagamentos.constantes.StatusPagamento;
import api.pagamentos.dto.PagamentoDTO;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.dto.PedidoStatusUsuarioDTO;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.exception.ResponseEnum;
import api.pagamentos.mapper.MapperPagamento;
import api.pagamentos.repository.PagamentoRepository;
import api.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    PagamentoRepository repository;
    @Autowired
    MapperPagamento mapper;
    @Autowired
    PedidosClient client;

    @Override
    public Page<PagamentoStatusDTO> getAllService(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("valor").ascending());
            return repository.findAll(pageable)
                    .map(mapper::pedidoEntityToStatusDTO);
        } catch (RuntimeException | Error e) {
            throw new PagamentosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar todos.");
        }
    }

    public Optional<PagamentoStatusDTO> saveService(PagamentoDTO pagamentoDTO) {
        try {
            if(!ObjectUtils.isEmpty(pagamentoDTO) && pagamentoDTO.usuarioDTO().isEmailValid(pagamentoDTO.usuarioDTO().email())) {
                PedidoStatusUsuarioDTO statusPedidoUsuario = client.getStatusEmailPedido(pagamentoDTO.usuarioDTO().email());
                if(!statusPedidoUsuario.statusPagamento().equals(StatusPagamento.PROCESSANDO) && !statusPedidoUsuario.statusPagamento().equals(StatusPagamento.RECUSADO)) {
                    PagamentoEntity pagamentoEntity = mapper.pagamentoDTOToPagamentoEntity(pagamentoDTO);
                    repository.save(pagamentoEntity);
                    return Optional.of(mapper.pedidoEntityToStatusDTO(pagamentoEntity));
                }
            }
            return Optional.empty();
        } catch (RuntimeException | Error e) {
            throw new PagamentosException(ResponseEnum.ERRO_INTERNO, "Falha ao salvar.");
        }
    }
}