package com.mazak.ofertowanie.repository;

import com.mazak.ofertowanie.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
