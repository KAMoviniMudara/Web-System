package com.example.Web.System.service;

import com.example.Web.System.Response.LoginResponse;
import com.example.Web.System.dto.LoginDTO;
import com.example.Web.System.dto.UserDTO;

public interface UserService {
    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);

    UserDTO getUserByEmail(String userEmail);
}
