package com.example.Web.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.Web.System.entity.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
