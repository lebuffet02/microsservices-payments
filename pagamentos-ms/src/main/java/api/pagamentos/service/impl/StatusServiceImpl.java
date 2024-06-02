package api.pagamentos.service.impl;

import api.pagamentos.constantes.StatusPagamento;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.exception.ResponseEnum;
import api.pagamentos.mapper.MapperPagamento;
import api.pagamentos.repository.PagamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatusServiceImpl {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MapperPagamento mapper;
    @Autowired
    PagamentoRepository repository;

//    private static final String SUBJECT = "Pagamento realizado com sucesso";
//    private static final String MESSAGE = "Pagamento realizado com sucesso";


    public List<PagamentoStatusDTO> buscarStatusService(StatusPagamento statusPagamento) {
        try {
            return repository.buscaStatusAtivo(statusPagamento)
                    .stream()
                    .map(p -> mapper.pedidoEntityToStatusDTO(p))
                    .collect(Collectors.toList());
        } catch (RuntimeException | Error e) {
            throw new PagamentosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar status.");
        }
    }


//    @Async
//    @Transactional
//    //@Override
//    public void atualizaStatusService(Long id, StatusPedido statusPedido) {
//        try {
//            PedidoEntity pedidoEntity = repository.findById(id)
//                    .orElseThrow(() -> new PedidosException(ResponseEnum.ERRO_INTERNO, "Id do pedido não foi encontrado."));
//            if(!pedidoEntity.getStatus().equals(statusPedido)) {
//                repository.atualizaStatusPedido(statusPedido, id);
//                log.info("toEmail: {} subJect: {} emailMessage: {}", pedidoEntity.getEmail(), SUBJECT, MESSAGE.concat(pedidoEntity.getNomeProduto())
//                        .concat(" foi separado."));
//                SimpleMailMessage mailMessage = new SimpleMailMessage();
//                mailMessage.setFrom("teste@gmail.com");
//                mailMessage.setTo(pedidoEntity.getEmail());
//                mailMessage.setSubject(SUBJECT);
//                mailMessage.setText(MESSAGE.concat(pedidoEntity.getNomeProduto()).concat(" foi separado(a)."));
//                mailSender.send(mailMessage);
//            }
//        } catch (RuntimeException | Error e) {
//            log.error("Exception: ".concat(e.getMessage()));
//            throw (e instanceof PedidosException) ?
//                    new PedidosException(ResponseEnum.ERRO_INTERNO, !e.getMessage().toLowerCase().contains("id")
//                            ? "Falha ao atualizar o status do pedido." : e.getMessage())
//                    : new EmailException(ResponseEnum.ERRO_INTERNO, e.getMessage());
//        }
//    }

}
