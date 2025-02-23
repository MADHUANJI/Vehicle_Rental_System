package com.Anji.UserLogin.repository;


import com.Anji.UserLogin.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // You can define custom query methods if needed
}
