package com.mazak.ofertowanie.controller;

import com.mazak.ofertowanie.model.Customer;
import com.mazak.ofertowanie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public List<Customer> search(@RequestParam(value = "name", required = false, defaultValue = "") String name){
        return customerService.search(name);
    }

    @PutMapping
    @ResponseStatus (HttpStatus.OK)
    public Customer update (@RequestBody Customer customer, @PathVariable("id") Long id){
        return customerService.update(customer);
    }

    @DeleteMapping
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public String remove(@PathVariable("id") Long id){
        customerService.remove(id);
        return String.format("Customer with id %s was remove", id);
    }

}
