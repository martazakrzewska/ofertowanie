package com.mazak.ofertowanie.service;

import com.mazak.ofertowanie.exception.CustomerExistsException;
import com.mazak.ofertowanie.exception.NotFoundException;
import com.mazak.ofertowanie.exception.ValidationException;
import com.mazak.ofertowanie.model.Customer;
import com.mazak.ofertowanie.model.dto.CustomerDto;
import com.mazak.ofertowanie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //public Customer create(Customer customer) {
      //  return customerRepository.save(customer);
    //}

   // public List<Customer> search(String name) {
       // return customerRepository.findByName(name);
    //}

    public Customer update(Customer customer) {
        return null;
    }

    public void remove(Long id) {
        if (!customerRepository.existsById(id)){
            throw new NotFoundException(String.format("Customer with id %s not found", id));
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> create(CustomerDto customerDto) {
        if (customerDto == null || customerDto.getName() == null
                || customerDto.getName().isEmpty()) {
            throw new ValidationException("Customer must hava name");
        }
        if (customerExists(customerDto.getName())) {
            throw new CustomerExistsException();
        }

        Customer newCustomer = new Customer(customerDto.getName(), customerDto.getNip(),
                customerDto.getAdress(), customerDto.getPhoneNumber());

        newCustomer = customerRepository.save(newCustomer);

        return Optional.ofNullable(newCustomer);
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    private boolean customerExists(String name){
        return customerRepository.findByName(name).isPresent();
    }


}
