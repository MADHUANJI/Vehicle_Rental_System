package com.Anji.UserLogin.service;

import com.Anji.UserLogin.dto.UserBean;
import com.Anji.UserLogin.model.UserEntity;
import com.Anji.UserLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void saveUser(UserBean userBean) {
        UserEntity user = new UserEntity();
        user.setUserName(userBean.getUserName());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        user.setPhoneNumber(userBean.getPhoneNumber());
        user.setCreatedAt(userBean.getCreatedAt());
        user.setUpdatedAt(userBean.getUpdatedAt());

        // Save the user to the database
        userRepository.save(user);
    }
}
