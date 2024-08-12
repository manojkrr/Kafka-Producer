package com.producer.kafkaproducer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(KafkaProducerException.class)
    public ResponseEntity<String> handleKafkaProducerException(KafkaProducerException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
