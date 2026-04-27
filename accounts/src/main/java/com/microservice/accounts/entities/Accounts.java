package com.microservice.accounts.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{

    @Id
    private Long accountNumber;

    private String accountType;
    private String branchAddresses;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
