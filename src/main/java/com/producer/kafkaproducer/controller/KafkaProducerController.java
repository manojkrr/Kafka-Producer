package com.producer.kafkaproducer.controller;

import com.producer.kafkaproducer.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return "✅ Message sent to Kafka topic asynchronously";
    }

    @PostMapping("/send-sync")
    public String sendMessageSynchronously(@RequestParam String message) {
        try {
            kafkaProducerService.sendSynchronously(message).get();
            return "✅ Message sent to Kafka topic synchronously";
        } catch (Exception e) {
            return "❌ Failed to send message: " + e.getMessage();
        }
    }
}
