package com.example.newssb.service.impl;

import com.example.newssb.converter.UserConverter;
import com.example.newssb.dto.UserDTO;
import com.example.newssb.entity.UserEntity;
import com.example.newssb.repository.UserRepository;
import com.example.newssb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> listDTO = new ArrayList<>();
        List<UserEntity> entityList = userRepository.findAll();
        for (UserEntity entity : entityList) {
            UserDTO dto = userConverter.toDTO(entity);
            listDTO.add(dto);
        }
        return listDTO;
    }

    @Override
    public void updateUser(UserDTO user) {
        userRepository.save(userConverter.toEntity(user));
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(userConverter::toDTO).get();
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        return userRepository.findOneByUserNameAndStatus(userName,1);
    }


}
