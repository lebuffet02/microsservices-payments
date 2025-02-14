package api.pagamentos.service.impl;

import api.pagamentos.client.PedidosClient;
import api.pagamentos.dto.request.PagamentoRequest;
import api.pagamentos.dto.response.PagamentoStatusDTO;
import api.pagamentos.dto.response.PedidoStatusDTO;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.exception.ResponseEnum;
import api.pagamentos.mapper.MapperPagamento;
import api.pagamentos.repository.PagamentoRepository;
import api.pagamentos.service.PagamentoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Slf4j
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

    @Transactional
    @Override
    public Optional<PagamentoStatusDTO> saveService(PagamentoRequest pagamentoRequest, Long pedidoId) {
        try {
            if(!ObjectUtils.isEmpty(pagamentoRequest) && pagamentoRequest.usuarioDTO().isEmailValid(pagamentoRequest.usuarioDTO().email())) {
                Optional<PedidoStatusDTO> statusPedidoUsuario = client.getStatusIdPedido(pedidoId);
                if(statusPedidoUsuario.isPresent() && statusPedidoUsuario.get().status().name().equalsIgnoreCase("aprovado")) {
                    PagamentoEntity pagamentoEntity = mapper.pagamentoDTOToPagamentoEntity(pagamentoRequest, statusPedidoUsuario.get(), false);
                    repository.save(pagamentoEntity);
                    return Optional.of(mapper.pedidoEntityToStatusDTO(pagamentoEntity));
                }
            }
            return Optional.empty();
        } catch (RuntimeException | Error e) {
            log.info("Falha ao salvar pagamento de pedido número: {} ", pedidoId);
            throw new PagamentosException(ResponseEnum.ERRO_EXTERNO, "Falha ao salvar pagamento.");
        }
    }
}