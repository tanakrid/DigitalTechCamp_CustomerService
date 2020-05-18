package com.digitalacademy.customer.support;

import com.digitalacademy.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSupportTest {

    public static Customer getNewCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Noon");
        customer.setLastName("Bow");
        customer.setEmail("bow@test.com");
        customer.setPhoneNo("0898989898");
        customer.setAge(18);
        return customer;
    }

    public static List<Customer> getCustomerList(){
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ryan");
        customer.setLastName("Giggs");
        customer.setEmail("iamgique@test.com");
        customer.setPhoneNo("0899999999");
        customer.setAge(22);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("David");
        customer.setLastName("Backham");
        customer.setEmail("david@test.com");
        customer.setPhoneNo("0899999910");
        customer.setAge(23);
        customerList.add(customer);
        return customerList;
    }

    public static List<Customer> getCustomerNameRyanList(){
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ryan");
        customer.setLastName("Giggs");
        customer.setEmail("iamgique@test.com");
        customer.setPhoneNo("0899999999");
        customer.setAge(22);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Ryan");
        customer.setLastName("Backham");
        customer.setEmail("david@test.com");
        customer.setPhoneNo("0899999910");
        customer.setAge(23);
        customerList.add(customer);
        return customerList;
    }

    public static Customer createNewCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Madrid");
        customer.setLastName("Bayern");
        customer.setEmail("test@test.com");
        customer.setPhoneNo("0898989898");
        customer.setAge(18);
        return customer;
    }
    public static Customer responseCreateNewCustomer(){
        Customer customer = new Customer();
        customer.setId(8L);
        customer.setFirstName("Madrid");
        customer.setLastName("Bayern");
        customer.setEmail("test@test.com");
        customer.setPhoneNo("0898989898");
        customer.setAge(18);
        return customer;
    }

    public static Customer getUpdateCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Noon2");
        customer.setLastName("Bow");
        customer.setEmail("bow@test.com");
        customer.setPhoneNo("0898989898");
        customer.setAge(18);
        return customer;
    }

}
