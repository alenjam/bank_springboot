package com.tortil.bankdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortil.bankdetails.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

}
