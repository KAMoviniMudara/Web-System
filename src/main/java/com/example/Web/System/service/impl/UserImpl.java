package com.example.Web.System.service.impl;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDto;
import com.example.Web.System.dto.UserDto;
import com.example.Web.System.repository.UserRepository;
import com.example.Web.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDto userDto) {
        String email = userDto.getEmail();

        Optional<org.springframework.security.core.userdetails.User> existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return "Email already exists";
        }

        com.example.Web.System.entity.User user = new com.example.Web.System.entity.User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getEmail(),
                this.passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole()
        );

        userRepository.save(user);
        return user.getUserName();
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        com.example.Web.System.entity.User user = userRepository.findByEmail(loginDto.getEmail());

        if (user != null) {
            String password = loginDto.getPassword();
            String encodedPassword = user.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                if ("ADMIN".equals(user.getRole())) {
                    return new LoginResponse("Login Success", true, "ADMIN");
                } else {
                    return new LoginResponse("You are not authorized to access", false, "USER");
                }
            } else {
                return new LoginResponse("Incorrect Password", false, null);
            }
        } else {
            return new LoginResponse("Email not exists", false, null);
        }
    }

    @Override
    public UserDto getUserByEmail(String userEmail) {
        return null;
    }
}
