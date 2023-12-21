package com.example.Web.System.service;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDto;
import com.example.Web.System.dto.UserDto;

import java.util.List;

public interface UserService {

    String addUser(UserDto userDto);
    LoginResponse loginUser(LoginDto loginDto);

    List<String> getAllUserNames();

    UserDto getUserByEmail(String userEmail);
}
