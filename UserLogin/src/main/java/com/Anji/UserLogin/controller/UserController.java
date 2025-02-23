package com.Anji.UserLogin.controller;

import com.Anji.UserLogin.dto.TempUserBean;
import com.Anji.UserLogin.dto.UserBean;
import com.Anji.UserLogin.service.UserService;
import com.Anji.UserLogin.service.OtpService;
import com.Anji.UserLogin.service.EmailService;
import com.Anji.UserLogin.helper.ApiResponse;
import com.Anji.UserLogin.entity.TempUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth/registration")
public class RegistrationController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    /**
     * Generate OTP for the given email and send it to the user's email address.
     * @param email - Email to which the OTP is sent.
     * @return - Response with success or error message.
     */
    @PostMapping("/generateOTP")
    public ResponseEntity<ApiResponse> generateOtp(@RequestParam String email) {
        // Generate OTP
        String otp = otpService.generateOtp();

        // Create TempUser object to store OTP and expiration time
        TempUserBean tempUser = new TempUserBean(email, otp, LocalDateTime.now().plusMinutes(5));

        // Save OTP in Redis
        otpService.saveOtpToRedis(tempUser);

        // Send OTP to the email
        emailService.sendOtpToEmail(email, otp);

        return ResponseEntity.ok(new ApiResponse(true, "OTP sent successfully to " + email));
    }

    /**
     * Save user details after OTP validation.
     * @param userBean - User details including email, phone number, etc.
     * @param otp - OTP entered by the user.
     * @return - Response with success or error message.
     */
    @PostMapping("/saveUser")
    public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody UserBean userBean, @RequestParam String otp) {
        // Validate OTP first
        if (!otpService.validateOtp(userBean.getEmail(), otp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Invalid or expired OTP"));
        }

        // Encrypt password before saving
        String encryptedPassword = userService.encryptPassword(userBean.getPassword());
        userBean.setPassword(encryptedPassword);

        // Save the user details in the database (timestamps are handled by JPA Auditing)
        userService.saveUser(userBean);

        // Clear OTP from Redis after successful registration
        otpService.removeOtpFromRedis(userBean.getEmail());

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
