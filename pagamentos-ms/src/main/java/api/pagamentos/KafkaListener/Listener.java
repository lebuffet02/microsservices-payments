//package api.pagamentos.KafkaListener;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class Listener {
//
//    @KafkaListener(topics = "ecommerce-pagamentos", groupId = "ecommerce-groupId")
//    public void consume(ConsumerRecord<String, String> payload) {
//        log.info("Topic: {}", "ecommerce-pagamentos");
//        log.info("Key: {}", payload.key());
//        log.info("Headers: {}", payload.headers());
//        log.info("Partion: {}", payload.partition());
//        log.info("Value: {}", payload.timestamp());
//        log.info("Value: {}", payload.value());
//    }
//}