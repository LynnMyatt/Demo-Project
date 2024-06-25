package com.tk.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountDTO {

    @NotEmpty(message = "Account number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    @Schema(description = "Account number of bank account", example = "34235633")
    private Long accountNumber;
    @NotEmpty(message = "Account type cannot be null or empty.")
    @Schema(description = "Account type of bank account", example = "Savings")
    private String accountType;
    @NotEmpty(message = "Branch address cannot be null or empty.")
    @Schema(description = "Bank branch address", example = "123 Bangkok")
    private String branchAddress;

}
