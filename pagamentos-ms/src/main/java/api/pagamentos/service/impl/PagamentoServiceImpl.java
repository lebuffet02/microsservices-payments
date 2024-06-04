package api.pagamentos.service.impl;

import api.pagamentos.client.PedidosClient;
import api.pagamentos.dto.*;
import api.pagamentos.dto.form.PagamentoForm;
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

    public Optional<PagamentoStatusDTO> saveService(PagamentoForm pagamentoForm, Long pedidoId) {
        try {
            if(!ObjectUtils.isEmpty(pagamentoForm) && pagamentoForm.usuarioDTO().isEmailValid(pagamentoForm.usuarioDTO().email())) {
                Optional<PedidoStatusDTO> statusPedidoUsuario = client.getStatusEmailPedido(pedidoId);
                if(statusPedidoUsuario.isPresent() && statusPedidoUsuario.get().status().name().equalsIgnoreCase("aprovado")) {
                    PagamentoEntity pagamentoEntity = mapper.pagamentoDTOToPagamentoEntity(pagamentoForm, statusPedidoUsuario.get());
                    repository.save(pagamentoEntity);
                    return Optional.of(mapper.pedidoEntityToStatusDTO(pagamentoEntity));
                }
            }
            return Optional.empty();
        } catch (RuntimeException | Error e) {
            throw new PagamentosException(ResponseEnum.ERRO_EXTERNO, "Falha ao salvar pagamento.");
        }
    }
}