package com.tk.accounts.service.impl;

import com.tk.accounts.assembler.AccountAssembler;
import com.tk.accounts.assembler.CustomerAssembler;
import com.tk.accounts.constants.AccountConstants;
import com.tk.accounts.dto.AccountDTO;
import com.tk.accounts.dto.CustomerDTO;
import com.tk.accounts.entity.Accounts;
import com.tk.accounts.entity.Customer;
import com.tk.accounts.exception.CustomerAlreadyExistException;
import com.tk.accounts.exception.ResourceNotFoundException;
import com.tk.accounts.repository.AccountsRepository;
import com.tk.accounts.repository.CustomerRepository;
import com.tk.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    /**
     *
     * @param customerDTO CustomerDTO Object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerAssembler.mapsToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with the given phone number ");
        }
        Customer savedCustomer = customerRepository.save(customer);
        saveAccount(savedCustomer);

    }

    private void saveAccount(Customer savedCustomer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(savedCustomer.getCustomerId());
        Long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccountNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        accountsRepository.save(accounts);
    }

    /**
     *
     * @param customerDTO CustomerDTO Object
     * @return boolean indicating if the update of the account is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if (accountDTO != null) {
            Accounts accounts = accountsRepository.findById(accountDTO.getAccountNumber()).orElseThrow(
                    () ->  new ResourceNotFoundException("Account", "phoneNumber", accountDTO.getAccountNumber().toString())
            );
            AccountAssembler.mapsToAccounts(accountDTO, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            CustomerAssembler.mapsToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     *
     * @param phoneNumber Input phone number
     * @return boolean indicating if the deletion of the account is successful or not
     */
    @Override
    public boolean deleteAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "phoneNumber", phoneNumber  )
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     *
     * @param phoneNumber Input phoneNumber of the custoer
     * @return CustomerDTO Object
     */
    @Override
    public CustomerDTO fetchAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "phoneNumber", phoneNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        AccountDTO accountDTO = AccountAssembler.mapsToAccountDTO(account, new AccountDTO());
        CustomerDTO customerDTO = CustomerAssembler.mapsToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }
}
