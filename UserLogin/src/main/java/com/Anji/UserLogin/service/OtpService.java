package com.Anji.UserLogin.service;

import com.Anji.UserLogin.dto.TempUserBean;
import com.Anji.UserLogin.entity.TempUser;
import com.Anji.UserLogin.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private TempUserRepository tempUserRepository;

    private static final String OTP_PREFIX = "OTP:";

    public String generateOtp() {
        // Generate a 6-digit OTP (alphanumeric)
        return String.format("%06d", (int) (Math.random() * 1000000));
    }

    public void saveOtpToRedis(TempUserBean tempUser) {
        // Save OTP with an expiration time of 5 minutes
        redisTemplate.opsForValue().set(OTP_PREFIX + tempUser.getEmail(), tempUser.getOtp(), 5, TimeUnit.MINUTES);
    }

    public boolean validateOtp(String email, String otp) {
        // Get OTP from Redis
        String storedOtp = redisTemplate.opsForValue().get(OTP_PREFIX + email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            // OTP matched
            return true;
        }
        return false;
    }

    public void removeOtpFromRedis(String email) {
        redisTemplate.delete(OTP_PREFIX + email);
    }
}
