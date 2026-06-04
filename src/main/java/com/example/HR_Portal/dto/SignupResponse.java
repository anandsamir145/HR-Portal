package com.example.HR_Portal.dto;

import com.example.HR_Portal.entity.User.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SignupResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Role role;
    private LocalDateTime createdAt;
    private String message;
}
