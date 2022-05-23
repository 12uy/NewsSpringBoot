package com.example.newssb.service;

import com.example.newssb.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUser();
    void updateUser(UserDTO user);
    void removeUserById(Long id);
    UserDTO getUserById(Long id);

    UserDTO getUserByUserName(String userName);
}
