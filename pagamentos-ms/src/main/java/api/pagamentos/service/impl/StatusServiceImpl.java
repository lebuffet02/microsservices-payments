package api.pagamentos.service.impl;

import api.pagamentos.constantes.StatusPedido;
import api.pagamentos.dto.PagamentoStatusDTO;
import api.pagamentos.entity.PagamentoEntity;
import api.pagamentos.entity.UsuarioEntity;
import api.pagamentos.exception.EmailException;
import api.pagamentos.exception.PagamentosException;
import api.pagamentos.exception.ResponseEnum;
import api.pagamentos.mapper.MapperPagamento;
import api.pagamentos.repository.PagamentoRepository;
import api.pagamentos.service.StatusService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MapperPagamento mapper;
    @Autowired
    PagamentoRepository repository;

    private static final String SUBJECT = "Pagamento realizado.";
    private static final String MESSAGE = "Pagamento realizado com sucesso ";
    private static final String SUBJECT_FAILED = "Pagamento recusado";
    private static final String MESSAGE_FAILED = "Pagamento não foi aceito para ";
    private static final String EMAIL_CONTACT = "teste@gmail.com";


    @Override
    public List<PagamentoStatusDTO> buscarStatusService(StatusPedido statusPedido) {
        try {
            return repository.buscaStatusAtivo(statusPedido)
                    .stream()
                    .map(p -> mapper.pedidoEntityToStatusDTO(p))
                    .collect(Collectors.toList());
        } catch (RuntimeException | Error e) {
            throw new PagamentosException(ResponseEnum.ERRO_INTERNO, "Falha ao buscar status.");
        }
    }

    @Async
    @Transactional
    @Override
    public void atualizaStatusService(Long id, StatusPedido statusPedido) {
        try {
            PagamentoEntity pagamentoEntity = repository.findById(id).orElseThrow(() -> new PagamentosException(ResponseEnum.ERRO_INTERNO, "Usuário não foi encontrado."));
            if(deveAtualizarStatus(pagamentoEntity, statusPedido)) {
                repository.atualizaStatusPedido(statusPedido, statusPedido.equals(StatusPedido.PAGAMENTO_RECEBIDO), id);
                enviarEmailNotificacao(pagamentoEntity.getUsuario(), pagamentoEntity.getNomeProduto(), statusPedido);
            }
        } catch (RuntimeException | Error e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw (e instanceof PagamentosException) ?
                    new PagamentosException(ResponseEnum.ERRO_INTERNO, !e.getMessage().toLowerCase().contains("usuário")
                            ? "Falha ao atualizar o status do pedido." : e.getMessage())
                    : new EmailException(ResponseEnum.ERRO_INTERNO, e.getMessage());
        }
    }

    private boolean deveAtualizarStatus(PagamentoEntity pagamentoEntity, StatusPedido statusPedido) {
        return (statusPedido.equals(StatusPedido.PAGAMENTO_RECEBIDO) || statusPedido.equals(StatusPedido.RECUSADO))
                && pagamentoEntity.getStatus().equals(StatusPedido.PAGO);
    }

    private void enviarEmailNotificacao(UsuarioEntity usuarioEntity, String produto, StatusPedido statusPedido) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(usuarioEntity.getEmail());
            mailMessage.setTo(EMAIL_CONTACT);
            mailMessage.setSubject(verificaStatusRecebido(statusPedido) ? SUBJECT : SUBJECT_FAILED);
            mailMessage.setText(verificaStatusRecebido(statusPedido) ? MESSAGE.concat(produto).concat(" foi separado(a).") : MESSAGE_FAILED.concat(produto));
            mailSender.send(mailMessage);
        } catch (RuntimeException | Error e) {
            throw new EmailException(ResponseEnum.ERRO_INTERNO, "Falha ao enviar email");
        }
    }

    private boolean verificaStatusRecebido(StatusPedido statusPedido) {
        return statusPedido.equals(StatusPedido.PAGAMENTO_RECEBIDO);
    }
}