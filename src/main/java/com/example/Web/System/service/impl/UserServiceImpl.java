package com.example.Web.System.service.impl;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDTO;
import com.example.Web.System.dto.UserDTO;
import com.example.Web.System.repository.UserRepository;
import com.example.Web.System.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        String email = userDTO.getEmail();

        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            logger.warn("User with email '{}' already exists", email);
            return "Email already exists";
        }

        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getRole()
        );

        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());

        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                if ("ADMIN".equals(user.getRole())) {
                    logger.info("User logged in as ADMIN: {}", user.getUsername());
                    return new LoginResponse("Login Success", true, "ADMIN");
                } else {
                    logger.warn("Login failed for user: {} - Not an admin", user.getUsername());
                    return new LoginResponse("You are not authorized to access", false, "USER");
                }
            } else {
                logger.warn("Password mismatch for user: {}", user.getUsername());
                return new LoginResponse("Incorrect Password", false, null);
            }
        } else {
            logger.warn("Email not found: {}", loginDTO.getEmail());
            return new LoginResponse("Email not exists", false, null);
        }
    }

    @Override
    public UserDTO getUserByEmail(String userEmail) {
        return null;
    }
}


