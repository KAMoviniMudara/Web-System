package com.example.Web.System.controller;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDto;
import com.example.Web.System.dto.UserDto;
import com.example.Web.System.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return handleValidationErrors(result);
        }

        String userName = userService.addUser(userDto);
        return ResponseEntity.ok("Registration successful for user: " + userName);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDto loginDto, BindingResult result) {
        if (result.hasErrors()) {
            return handleValidationErrors(result);
        }

        LoginResponse loginResponse = userService.loginUser(loginDto);
        if (loginResponse.isStatus()) {
            logger.info("User login successful for email: {}", loginDto.getEmail());
        } else {
            logger.warn("User login failed for email: {}", loginDto.getEmail());
        }

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getUserProfile(@AuthenticationPrincipal Principal principal) {
        if (principal != null) {
            String userEmail = principal.getName();
            logger.info("Retrieved user profile for email: {}", userEmail);

            UserDto userDto = userService.getUserByEmail(userEmail);
            return ResponseEntity.ok(userDto);
        } else {
            logger.warn("User profile request without authenticated principal");
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/userNames")
    public ResponseEntity<Object> getAllUserNames() {
        List<String> userNames = userService.getAllUserNames();
        return ResponseEntity.ok(userNames);
    }


    private ResponseEntity<Object> handleValidationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
