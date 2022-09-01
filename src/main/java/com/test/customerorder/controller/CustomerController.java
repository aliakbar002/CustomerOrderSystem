package com.test.customerorder.controller;

import com.test.customerorder.dto.CustomerDto;
import com.test.customerorder.entity.Customer;
import com.test.customerorder.entity.Order;
import com.test.customerorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return  customerService.addCustomer(customer);
    }

    @GetMapping("{id}")
    public CustomerDto getCustomerDto(@PathVariable int id){
        return customerService.getCustomerDto(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getCustomerDetails();
    }

}
