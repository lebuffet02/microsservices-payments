package api.pedidos.service.impl;

import api.pedidos.constantes.StatusPedido;
import api.pedidos.dto.PedidoStatusDTO;
import api.pedidos.entity.PedidoEntity;
import api.pedidos.exception.EmailException;
import api.pedidos.exception.PedidosException;
import api.pedidos.exception.ResponseEnum;
import api.pedidos.mapper.MapperPedido;
import api.pedidos.repository.PedidoRepository;
import api.pedidos.service.StatusService;
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
    MapperPedido mapper;
    @Autowired
    PedidoRepository repository;
    @Autowired
    JavaMailSender mailSender;

    private static final String SUBJECT = "Pedido realizado com sucesso";
    private static final String MESSAGE = "Pedido realizado com sucesso";


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

    @Async
    @Transactional
    @Override
    public void atualizaStatusService(Long id, StatusPedido statusPedido) {
        try {
            PedidoEntity pedidoEntity = repository.findById(id)
                    .orElseThrow(() -> new PedidosException(ResponseEnum.ERRO_INTERNO, "Id do pedido não foi encontrado."));
            if(!pedidoEntity.getStatus().equals(statusPedido)) {
                repository.atualizaStatusPedido(statusPedido, id);
                log.info("toEmail: {} subJect: {} emailMessage: {}", pedidoEntity.getEmail(), SUBJECT, MESSAGE.concat(pedidoEntity.getNomeProduto())
                        .concat(" foi separado."));
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom("teste@gmail.com");
                mailMessage.setTo(pedidoEntity.getEmail());
                mailMessage.setSubject(SUBJECT);
                mailMessage.setText(MESSAGE.concat(pedidoEntity.getNomeProduto()).concat(" foi separado."));
                mailSender.send(mailMessage);
            }
        } catch (RuntimeException | Error e) {
            log.error("Exception: ".concat(e.getMessage()));
            throw (e instanceof PedidosException) ?
                    new PedidosException(ResponseEnum.ERRO_INTERNO, !e.getMessage().toLowerCase().contains("id")
                            ? "Falha ao atualizar o status do pedido." : e.getMessage())
                    : new EmailException(ResponseEnum.ERRO_INTERNO, e.getMessage());
        }
    }
}