package com.microservice.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "TheRealCodeShow"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;


    @Schema(
            description = "Email address of the customer", example = "tutor@therealcodeshow.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile salvadoran number of the customer", example = "1122-3344"
    )
    // Accept the salvadoran mobile number format like: "1122-3344 in the regex"
    @NotEmpty(message = "Mobile number can not be a null or empty")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "Mobile number should be a valid salvadoran mobile number")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDto account;
}
