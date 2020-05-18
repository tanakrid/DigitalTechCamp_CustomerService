package com.digitalacademy.customer.service;

import com.digitalacademy.customer.support.CustomerSupportTest;
import com.digitalacademy.model.Customer;
import com.digitalacademy.repositories.CustomerRepository;
import com.digitalacademy.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @DisplayName("Test get all customer should return list")
    @Test
    void testGetAllCustomer() {
        when(customerRepository.findAll()).thenReturn(CustomerSupportTest.getCustomerList());
        List<Customer> resp = customerService.getCustomerList();

        assertEquals(1, resp.get(0).getId().intValue());
        assertEquals("Ryan", resp.get(0).getFirstName());
        assertEquals("Giggs", resp.get(0).getLastName());
        assertEquals("iamgique@test.com", resp.get(0).getEmail());
        assertEquals("0899999999", resp.get(0).getPhoneNo());
        assertEquals(22, resp.get(0).getAge().intValue());

        assertEquals(2, resp.get(1).getId().intValue());
        assertEquals("David", resp.get(1).getFirstName());
        assertEquals("Backham", resp.get(1).getLastName());
        assertEquals("david@test.com", resp.get(1).getEmail());
        assertEquals("0899999910", resp.get(1).getPhoneNo());
        assertEquals(23, resp.get(1).getAge().intValue());

    }

    @DisplayName("Test get all customer by id should return list")
    @Test
    void testGetAllCustomerById() {
        Long reqParam = 1L;
        when(customerRepository.findAllById(reqParam)).thenReturn(CustomerSupportTest.getCustomerList().get(0));
        Customer resp = customerService.getCustomer(reqParam);

        assertEquals(1, resp.getId().intValue());
        assertEquals("Ryan", resp.getFirstName());
        assertEquals("Giggs", resp.getLastName());
        assertEquals("iamgique@test.com", resp.getEmail());
        assertEquals("0899999999", resp.getPhoneNo());
        assertEquals(22, resp.getAge().intValue());
    }


    @DisplayName("Test create customer should return new customer")
    @Test
    void testCreateCustomer() {
        Customer reqCustomer = new Customer();
        reqCustomer.setFirstName("Noon");
        reqCustomer.setLastName("Bow");
        reqCustomer.setEmail("bow@test.com");
        reqCustomer.setPhoneNo("0898989898");
        reqCustomer.setAge(18);

        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getNewCustomer());
        Customer resp = customerService.createCustomer(reqCustomer);

        assertEquals(1, resp.getId().intValue());
        assertEquals("Noon", resp.getFirstName());
        assertEquals("Bow", resp.getLastName());
        assertEquals("bow@test.com", resp.getEmail());
        assertEquals("0898989898", resp.getPhoneNo());
        assertEquals(18, resp.getAge().intValue());
    }

    @DisplayName("Test update customer should return success")
    @Test
    void testUpdateCustomer() {
        Long reqId = 1L;
        Customer reqCustomer = new Customer();
        reqCustomer.setFirstName("Noon");
        reqCustomer.setLastName("Bow");
        reqCustomer.setEmail("bow@test.com");
        reqCustomer.setPhoneNo("0898989898");
        reqCustomer.setAge(18);

        when(customerRepository.findAllById(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());
        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getNewCustomer());
        Customer resp = customerService.updateCustomer(reqId, reqCustomer);

        assertEquals(1, resp.getId().intValue());
        assertEquals("Noon", resp.getFirstName());
        assertEquals("Bow", resp.getLastName());
        assertEquals("bow@test.com", resp.getEmail());
        assertEquals("0898989898", resp.getPhoneNo());
        assertEquals(18, resp.getAge().intValue());
    }

    @DisplayName("Test update customer should return fail")
    @Test
    void testUpdateCustomerFail(){
        Long reqId = 4L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Noon");
        reqCustomer.setLastName("Bow");
        reqCustomer.setEmail("noon@gmail.com");
        reqCustomer.setPhoneNo("1234566");
        reqCustomer.setAge(18);
        when(customerRepository.findAllById(reqId)).thenReturn(null);
        Customer resp =  customerService.updateCustomer(reqId, reqCustomer);
        assertEquals(null, resp);
    }































    @DisplayName("Test delete customer should return true")
    @Test
    void testDeleteCustomer(){
        Long reqId = 1L;
        doNothing().when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteByID(reqId);
        assertTrue(resp);
    }

    @DisplayName("Test delete customer should return fail")
    @Test
    void testDeleteCustomerFail(){
        Long reqId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteByID(reqId);
        assertFalse(resp);
    }

    @DisplayName("Test get list of customer by them name should return list")
    @Test
    void testGetCustomerName(){
        String reqName = "Ryan";
        when(customerRepository.findByFirstName(reqName)).thenReturn(CustomerSupportTest.getCustomerNameRyanList());
        List<Customer> resp = customerService.getCustomerName(reqName);


        assertEquals(1, resp.get(0).getId().intValue());
        assertEquals("Ryan", resp.get(0).getFirstName());
        assertEquals("Giggs", resp.get(0).getLastName());
        assertEquals("iamgique@test.com", resp.get(0).getEmail());
        assertEquals("0899999999", resp.get(0).getPhoneNo());
        assertEquals(22, resp.get(0).getAge().intValue());

        assertEquals(2, resp.get(1).getId().intValue());
        assertEquals("Ryan", resp.get(1).getFirstName());
        assertEquals("Backham", resp.get(1).getLastName());
        assertEquals("david@test.com", resp.get(1).getEmail());
        assertEquals("0899999910", resp.get(1).getPhoneNo());
        assertEquals(23, resp.get(1).getAge().intValue());
    }
}
