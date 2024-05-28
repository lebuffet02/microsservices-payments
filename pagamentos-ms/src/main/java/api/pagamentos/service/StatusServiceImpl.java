package api.pagamentos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatusServiceImpl {

//    @Autowired
//    JavaMailSender mailSender;

//    private static final String SUBJECT = "Pagamento realizado com sucesso";
//    private static final String MESSAGE = "Pagamento realizado com sucesso";


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
