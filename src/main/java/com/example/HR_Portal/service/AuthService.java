package com.example.HR_Portal.service;

import com.example.HR_Portal.dto.SignupRequest;
import com.example.HR_Portal.dto.SignupResponse;
import com.example.HR_Portal.entity.User;
import com.example.HR_Portal.exception.EmailAlreadyExistsException;
import com.example.HR_Portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    // Using BCrypt directly since Spring Security is not added yet.
    // Replace with PasswordEncoder bean once Spring Security is configured.
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SignupResponse signup(SignupRequest request) {
        // Check for duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Build user entity
        User user = User.builder()
                .fullName(request.getFullName().trim())
                .email(request.getEmail().toLowerCase().trim())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole() != null ? request.getRole() : User.Role.EMPLOYEE)
                .build();

        User saved = userRepository.save(user);

        return SignupResponse.builder()
                .id(saved.getId())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .phone(saved.getPhone())
                .role(saved.getRole())
                .createdAt(saved.getCreatedAt())
                .message("Account created successfully")
                .build();
    }
}
