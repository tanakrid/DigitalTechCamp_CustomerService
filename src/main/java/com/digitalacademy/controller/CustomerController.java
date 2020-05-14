package com.digitalacademy.controller;

import com.digitalacademy.model.Customer;
import com.digitalacademy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController() {

    }
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/list")
    public List<Customer> getAllCustomer(){
        return customerService.getCustomerList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        if(customer != null){
            return ResponseEntity.ok(customer);

        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
