package api.pedidos.kafkaProducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public Producer(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> void send(T message) {
        log.info("Payload enviado: {}",  message);
        kafkaTemplate.send("ecommerce-pagamentos", message);
    }
}