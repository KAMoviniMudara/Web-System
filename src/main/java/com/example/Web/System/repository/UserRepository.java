package com.example.Web.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {
    Optional<User> findOneByEmailAndPassword(String email , String password);

    User findByEmail(String email);
}

