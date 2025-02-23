package com.Anji.UserLogin.helper;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class OtpHelper {

    // OTP length configuration
    private static final int OTP_LENGTH = 6;
    private static final String OTP_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final SecureRandom secureRandom = new SecureRandom();

    // Method to generate a random OTP
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();

        // Generate OTP of length OTP_LENGTH
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = secureRandom.nextInt(OTP_CHARACTERS.length());  // Random index
            otp.append(OTP_CHARACTERS.charAt(index));  // Append character at that index
        }

        return otp.toString();
    }

    // Method to encode OTP using Base64 (to store in Redis or secure way)
    public String encodeOtp(String otp) {
        return Base64.getEncoder().encodeToString(otp.getBytes());
    }

    // Method to validate OTP (Base64 decoding and matching with original OTP)
    public boolean validateOtp(String originalOtp, String encodedOtp) {
        String decodedOtp = new String(Base64.getDecoder().decode(encodedOtp));
        return decodedOtp.equals(originalOtp);
    }
}
