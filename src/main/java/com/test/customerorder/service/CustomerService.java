package com.test.customerorder.service;

import com.test.customerorder.dto.CustomerDto;
import com.test.customerorder.entity.Customer;
import com.test.customerorder.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //Post Mapping
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //Get By Id Mapping
    public CustomerDto getCustomerDto(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        CustomerDto customerDto = new CustomerDto();
        assert customer != null;
        customerDto.setCustomer(customer.getCustomer());
        return customerDto;
    }

    //Get All Mapping
    public List<Customer> getCustomerDetails() {
        return new ArrayList<>(customerRepository.findAll());
    }

}

