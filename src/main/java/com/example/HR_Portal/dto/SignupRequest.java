package com.example.HR_Portal.dto;

import com.example.HR_Portal.entity.User.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit"
    )
    private String password;

    @Pattern(regexp = "^$|^[+]?[0-9]{7,15}$", message = "Please provide a valid phone number")
    private String phone;

    private Role role = Role.EMPLOYEE; // defaults to EMPLOYEE if not provided
}
