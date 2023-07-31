package com.tortil.bankdetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortil.bankdetails.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, String> {
	List<Branch> findByCity(String city);
	List<Branch> findByCityContaining(String city);
	List<Branch> findByNameContaining(String name);
}
