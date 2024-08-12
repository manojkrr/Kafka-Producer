package com.producer.kafkaproducer.controller;

import com.producer.kafkaproducer.service.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage(message);
        return "Message sent to Kafka topic";
    }

    @GetMapping("/send-sync")
    public String sendMessageSynchronously(@RequestParam("message") String message) {
        try {
            kafkaProducerService.sendSynchronously(message).get();
        } catch (Exception e) {
            return "Failed to send message to Kafka topic";
        }
        return "Message sent to Kafka topic";
    }
}
