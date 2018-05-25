package com.mazak.ofertowanie.controller;

import com.mazak.ofertowanie.exception.CustomerExistsException;
import com.mazak.ofertowanie.model.Customer;
import com.mazak.ofertowanie.model.dto.CustomerDto;
import com.mazak.ofertowanie.response.ResponseFactory;
import com.mazak.ofertowanie.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/customer/")
@CrossOrigin
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Customer> create(@RequestBody CustomerDto customerDto) {
        try {
            Optional<Customer> customer = customerService.create(customerDto);

            if (customer.isPresent()) {
                return ResponseFactory.created(customer.get());
            }
        } catch (CustomerExistsException e) {
            e.printStackTrace();
        }
        return ResponseFactory.badRequest();
    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> get(@PathVariable(name = "id") Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);

        if (customer.isPresent()) {
            return ResponseFactory.ok(customer.get());
        }
        return ResponseFactory.badRequest();
    }
    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.getAll();

        return ResponseFactory.ok(customers);
    }
}
