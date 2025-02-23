package com.Anji.UserLogin.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBean {

    private Long userId;  // Primary key for the user

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String userName;  // User's name

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;  // User's email address

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;  // User's password

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;  // User's phone number (10 digits exactly)

    @Pattern(regexp = "^\\d{10}$", message = "Alternate phone number must be exactly 10 digits")
    private String alternatePhoneNumber;  // Optional alternate phone number (10 digits exactly)

    private LocalDateTime createdAt;  // User registration timestamp
    private LocalDateTime updatedAt;  // Last updated timestamp
}
