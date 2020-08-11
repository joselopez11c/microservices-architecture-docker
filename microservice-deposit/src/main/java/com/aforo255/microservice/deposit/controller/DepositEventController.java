package com.aforo255.microservice.deposit.controller;

import com.aforo255.microservice.deposit.domain.Transaction;
import com.aforo255.microservice.deposit.producer.DepositEventProducer;
import com.aforo255.microservice.deposit.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositEventController {

    private final Logger log = LoggerFactory.getLogger(DepositEventController.class);

    @Autowired
    DepositEventProducer depositEventProducer;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/v1/depositevent")
    public ResponseEntity<Transaction> saveDepositEvent(@RequestBody Transaction transactionEvent)
            throws JsonProcessingException {

        Transaction transactionResponse = transactionService.save(transactionEvent);
        log.info("Despues de guardar transactionEvent");
        log.info("Antes  de sendDepositEvent");
        depositEventProducer.sendDepositEvent(transactionResponse);
        log.info("Despues  de sendDepositEvent");
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

}
