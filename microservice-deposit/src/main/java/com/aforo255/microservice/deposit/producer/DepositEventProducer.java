package com.aforo255.microservice.deposit.producer;

import com.aforo255.microservice.deposit.domain.Transaction;
import com.aforo255.microservice.deposit.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DepositEventProducer {
    private final Logger log = LoggerFactory.getLogger(DepositEventProducer.class);

    String topic = "transaction-events";

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TransactionService transactionService;

    public void sendDepositEvent(Transaction depositEvent) throws JsonProcessingException {
        Integer key = depositEvent.getId();
        String value = objectMapper.writeValueAsString(depositEvent);
        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topic);
        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handlerFailed(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handlerSuccess(key, value, result);
            }
        });

    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topicName) {
        List<Header> recordHeaders = Stream.of(new RecordHeader("deposit-event-source", "scanner".getBytes()))
                .collect(Collectors.toList());

        return new ProducerRecord<>(topicName, null, key, value, recordHeaders);
    }

    @SneakyThrows
    private void handlerFailed(Integer key, String value, Throwable exception) {
        log.error("The key is: {}", key);
        log.error("The value is: {}", value);
        log.error("Error Sending the message and the exception is {}", exception.getMessage());
        try {
            throw exception;
        } catch (Exception throwable) {
            log.error("Error in OnFaile: {}", throwable.getMessage());
        }
    }

    private void handlerSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message send successfully for the key: {} and the value is {}, partition is {}",
                key, value, result.getRecordMetadata().partition());
    }
}
