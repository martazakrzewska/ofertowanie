package com.mazak.ofertowanie.service;

import com.mazak.ofertowanie.model.Customer;
import com.mazak.ofertowanie.model.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Optional<Customer> create (CustomerDto customerDto);

    Optional<Customer> getCustomer(Long id);

    List<Customer> getAll();

}
