package com.aforo255.microservice.deposit.repository;

import com.aforo255.microservice.deposit.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
