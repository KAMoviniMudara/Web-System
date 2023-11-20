package com.example.Web.System.controller;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDTO;
import com.example.Web.System.dto.UserDTO;
import com.example.Web.System.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        logger.info("Received UserDto email: {}", userDTO.getEmail());
        logger.info("Received UserDto role: {}", userDTO.getRole());

        String userName = userService.addUser(userDTO);
        logger.info("User registered: {}", userName);

        return ResponseEntity.ok("Registration successful for user: " + userName);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        logger.info("Received login request for email: {}", loginDTO.getEmail());

        LoginResponse loginResponse = userService.loginUser(loginDTO);
        if (loginResponse.isStatus()) {
            logger.info("User login successful for email: {}", loginDTO.getEmail());
        } else {
            logger.warn("User login failed for email: {}", loginDTO.getEmail());
        }

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@AuthenticationPrincipal Principal principal) {
        if (principal != null) {
            String userEmail = principal.getName();
            logger.info("Retrieved user profile for email: {}", userEmail);

            UserDTO userDTO = userService.getUserByEmail(userEmail);
            return ResponseEntity.ok(userDTO);
        } else {
            logger.warn("User profile request without authenticated principal");
            return ResponseEntity.badRequest().body(null);
        }
    }
}
