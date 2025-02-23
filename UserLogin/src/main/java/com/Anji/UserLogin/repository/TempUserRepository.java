package com.Anji.UserLogin.repository;

import com.Anji.UserLogin.model.TempUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository {

    void save(TempUser tempUser);
    TempUser findByEmail(String email);
    void deleteByEmail(String email);
}
