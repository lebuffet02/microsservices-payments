package api.pagamentos.service.impl;

import api.pagamentos.constantes.StatusPagamento;
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
    private static final String MESSAGE = "Pagamento realizado com sucesso";
    private static final String SUBJECT_FAILED = "Pagamento recusado";
    private static final String MESSAGE_FAILED = "Pagamento não foi aceito para ";


    @Override
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

    @Async
    @Transactional
    @Override
    public void atualizaStatusService(Long id, StatusPagamento statusPagamento) {
        try {
            PagamentoEntity pagamentoEntity = repository.findById(id).orElseThrow(() -> new PagamentosException(ResponseEnum.ERRO_INTERNO, "Usuário não foi encontrado."));
            if(!pagamentoEntity.getStatus().equals(statusPagamento)) {
                repository.atualizaStatusPedido(statusPagamento, id);
                enviarEmailNotificacao(pagamentoEntity.getUsuario(), pagamentoEntity, statusPagamento);
            }
        } catch (RuntimeException | Error e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw (e instanceof PagamentosException) ?
                    new PagamentosException(ResponseEnum.ERRO_INTERNO, !e.getMessage().toLowerCase().contains("usuário")
                            ? "Falha ao atualizar o status do pedido." : e.getMessage())
                    : new EmailException(ResponseEnum.ERRO_INTERNO, e.getMessage());
        }
    }

    private void enviarEmailNotificacao(UsuarioEntity usuarioEntity, PagamentoEntity pagamentoEntity, StatusPagamento statusPagamento) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("teste@gmail.com"); // ALTERAR ESSE EMAIL
            mailMessage.setTo(usuarioEntity.getEmail());
            if (statusPagamento.equals(StatusPagamento.RECUSADO)) {
                log.info("toEmail: {} subJect: {} emailMessage: {}", usuarioEntity.getEmail(), SUBJECT, MESSAGE.concat(pagamentoEntity.getNomeProduto())
                        .concat(" foi separado."));
                mailMessage.setSubject(SUBJECT);
                mailMessage.setText(MESSAGE.concat(pagamentoEntity.getNomeProduto()).concat(" foi separado(a)."));
                mailSender.send(mailMessage);
            } else {
                mailMessage.setSubject(SUBJECT_FAILED);
                mailMessage.setText(MESSAGE_FAILED.concat(pagamentoEntity.getNomeProduto()));
                mailSender.send(mailMessage);
            }
        } catch (RuntimeException | Error e) {
            throw new EmailException(ResponseEnum.ERRO_INTERNO, "Falha ao enviar email");
        }
    }
}
