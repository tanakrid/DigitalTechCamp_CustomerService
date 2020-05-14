package com.digitalacademy.service;

import com.digitalacademy.model.Customer;
import com.digitalacademy.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id){

        return customerRepository.findAllById(id);

    }

    public List<Customer> getCustomerName(String name){
        return customerRepository.findByFirstName(name);
    }

    public Customer createCustomer(Customer body){
        return customerRepository.save(body);
    }

    public Customer updateCustomer(Long id, Customer customerReq) {
        return customerRepository.findAllById(id) != null ?
                customerRepository.save(customerReq) :
                null;
    }

    public boolean deleteByID (Long id){
        try {
            customerRepository.deleteById((id));
            return true;
        }catch (EmptyResultDataAccessException e){
            return  false;

        }
    }
}
