package com.microservice.accounts.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    // Accept the salvadoran mobile number format like: "1122-3344 in the regex"
    @NotEmpty(message = "Mobile number can not be a null or empty")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "Mobile number should be a valid salvadoran mobile number")
    private String mobileNumber;

    private AccountDto account;
}
