package com.tk.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customers",
        description = "Schema to hold customer information"
)
public class CustomerDTO {

    @NotEmpty(message = "Customer name cannot be null or empty.")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30.")
    @Schema(
            description = "Name of the customer", example = "Lynn Myat"
    )
    private String name;
    @NotEmpty(message = "Customer email cannot be null or empty.")
    @Email(message = "Email address should be a valid value.")
    @Schema(
            description = "Email address of the customer", example = "lynn@gmail.com"
    )
    private String email;
    @NotEmpty(message = "Customer phone number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Phone number of the customer", example = "063423523"
    )
    private String phoneNumber;
    @Schema(
            description = "Account details of the customer"
    )
    private AccountDTO accountDTO;
}
