package com.microservice.accounts.services.impl;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dtos.AccountDto;
import com.microservice.accounts.dtos.CustomerDto;
import com.microservice.accounts.entities.Accounts;
import com.microservice.accounts.entities.Customer;
import com.microservice.accounts.exceptions.CustomerAlreadyExistsException;
import com.microservice.accounts.exceptions.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repositories.AccountsRepository;
import com.microservice.accounts.repositories.CustomersRepository;
import com.microservice.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomersRepository customersRepository;


    /**
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional = this.customersRepository.findByMobileNumber(customer.getMobileNumber());

        if(customerOptional.isPresent())
            throw new CustomerAlreadyExistsException(
                    "Customer already exists with mobile number: " + customer.getMobileNumber()
            );

        Customer savedCustomer = this.customersRepository.save(customer);

        this.accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = this.customersRepository.findByMobileNumber(
                mobileNumber
        ).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = this.accountsRepository.findByCustomerId(
                customer.getCustomerId()
        ).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );


        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccount(AccountsMapper.mapToAccountsDto(
                accounts,
                new AccountDto()
        ));

        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        AccountDto accountDto = customerDto.getAccount();

        if(accountDto != null) {
            Accounts accounts = this.accountsRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountDto, accounts);
            accounts = this.accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = this.customersRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            this.customersRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = this.customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        long deletedCount = this.accountsRepository.deleteByCustomerId(customer.getCustomerId());
        if(deletedCount <= 0)
            return false;

        this.customersRepository.deleteById(customer.getCustomerId());

        return true;
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
