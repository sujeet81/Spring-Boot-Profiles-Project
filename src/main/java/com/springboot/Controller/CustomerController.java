package com.springboot.Controller;

import com.springboot.Dto.CustomerDto;
import com.springboot.Service.CustomerService;
import com.springboot.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public List<Customer> saveCustomer(@RequestBody List<Customer> customers){
        return customerService.addNewCustomer(customers);
    }

    @GetMapping
    public List<CustomerDto> fetchAllCustomer(){
        return customerService.getCustomer();
    }


}
