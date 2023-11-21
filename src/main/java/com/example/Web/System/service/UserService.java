package com.example.Web.System.service;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDto;
import com.example.Web.System.dto.UserDto;

public interface UserService {

    String addUser(UserDto userDto);
    LoginResponse loginUser(LoginDto loginDto);

    UserDto getUserByEmail(String userEmail);
}
