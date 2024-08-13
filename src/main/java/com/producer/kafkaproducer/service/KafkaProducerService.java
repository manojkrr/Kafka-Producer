package com.producer.kafkaproducer.service;

import com.producer.kafkaproducer.exception.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RetryTemplate retryTemplate;
    private final String kafkaTopicName;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, RetryTemplate retryTemplate, String kafkaTopicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.retryTemplate = retryTemplate;
        this.kafkaTopicName = kafkaTopicName;
    }

    public void sendMessage(String message) {
        try {
            retryTemplate.execute(context -> {
                return kafkaTemplate.send(kafkaTopicName, message); // .get() to block until the send completes
            });
        } catch (Exception e) {
            throw new KafkaProducerException("Failed to send message " + e);
        }
    }

    public CompletableFuture<SendResult<String, String>> sendSynchronously(String message) {
        return retryTemplate.execute(context -> kafkaTemplate.send(kafkaTopicName, message));
    }
}
