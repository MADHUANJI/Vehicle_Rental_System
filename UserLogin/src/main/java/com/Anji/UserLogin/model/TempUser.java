package com.Anji.UserLogin.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempUser {

    private String email;
    private String otp;
    private LocalDateTime expirationTime;
}
