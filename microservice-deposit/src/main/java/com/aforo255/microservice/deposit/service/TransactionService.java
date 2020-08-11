package com.aforo255.microservice.deposit.service;

import com.aforo255.microservice.deposit.domain.Transaction;

public interface TransactionService {

    Transaction findById(Integer id);

    Transaction save(Transaction entity);
}
