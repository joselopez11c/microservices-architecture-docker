package com.aforo255.microservice.deposit.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
@Setter @Getter
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double amount;
    private String type;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "account_id")
    private Integer accountId;

}
