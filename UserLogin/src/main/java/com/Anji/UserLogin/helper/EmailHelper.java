package com.Anji.UserLogin.helper;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailHelper {

    private final JavaMailSender javaMailSender;

    public EmailHelper(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Method to send OTP email
    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(email);
        helper.setSubject("Your OTP Code");
        helper.setText("Your OTP code is: " + otp);

        // Send the email
        javaMailSender.send(mimeMessage);
    }
}
