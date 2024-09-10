package com.springboot.Service;

import com.springboot.Dto.CustomerDto;
import com.springboot.Repository.CustomerRepository;
import com.springboot.entity.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile(value = {"dev","stg","prod"})
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Value("${application.message}")
    private String message;

   @PostConstruct
    public void init(){
        System.out.println("***************"+message);
    }

    public List<Customer> addNewCustomer(List<Customer> customers){
        return customerRepository.saveAll(customers);
    }

    public List<CustomerDto> getCustomer(){
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(customer -> new CustomerDto(customer.getId(),customer.getName(),customer.getEmail(),
                customer.getPhone() ,getDateFormat(customer.getDob()))).collect(Collectors.toList());
    }

    public String getDateFormat(Date date){
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
