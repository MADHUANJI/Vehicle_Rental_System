package com.Anji.UserLogin.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempUserBean {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;  // User's email (used as the unique key in Redis)

    @NotBlank(message = "OTP cannot be blank")
    @Size(min = 6, max = 6, message = "OTP must be exactly 6 digits")
    private String otp;  // One-Time Password for validation

    @NotNull(message = "Expiration time cannot be null")
    private LocalDateTime expirationTime;  // Expiry time for OTP
}
