package com.microservice.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts"
)
public class AccountDto {

    @NotEmpty(message = "Account Number can not be a null or empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account Number should be a valid 10 digit number")
    @Schema(
            description = "Account Number of TheRealCodeShow - Bank"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be a null or empty")
    @Schema(
            description = "Account type of TheRealCodeShow account", example = "Savings"
    )
    private String accountType;

    @Schema(
            description = "TheRealCodeShow Bank branch"
    )
    @NotEmpty(message = "Branch Address can not be a null or empty")
    private String branchAddress;
}
