package com.tk.accounts.assembler;

import com.tk.accounts.dto.CustomerDTO;
import com.tk.accounts.entity.Customer;

public class CustomerAssembler {

    public static CustomerDTO mapsToCustomerDTO(Customer customer, CustomerDTO customerDTO) {

        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        return customerDTO;
    }

    public static Customer mapsToCustomer(CustomerDTO customerDTO, Customer customer) {

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        return customer;
    }
}
