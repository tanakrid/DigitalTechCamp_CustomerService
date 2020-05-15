package com.digitalacademy.controller;

import com.digitalacademy.model.Customer;
import com.digitalacademy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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

    @GetMapping(params = "name")
    public ResponseEntity<?> getCustomerByName(@RequestParam(value = "name") String name){
        List<Customer> customers = customerService.getCustomerName(name);
        return customers != null && !customers.isEmpty() ? ResponseEntity.ok(customers) : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer body){
        Customer customer = customerService.createCustomer(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCustomer(@PathVariable Long id, @Valid @RequestBody Customer body){
        body.setId(id);
        Customer customer = customerService.updateCustomer(id, body);
        return customer != null ? ResponseEntity.ok(customer)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        return customerService.deleteByID(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
