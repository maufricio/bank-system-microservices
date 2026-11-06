package com.microservice.accounts.repositories;

import com.microservice.accounts.entities.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    // We put the annotations below to make sure that the database is updated when we delete an account
    @Transactional
    @Modifying
    long deleteByCustomerId(Long customerId);
}
