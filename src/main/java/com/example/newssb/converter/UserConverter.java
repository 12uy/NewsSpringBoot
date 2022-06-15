package com.example.newssb.converter;

import com.example.newssb.dto.UserDTO;
import com.example.newssb.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setRoleIds(userEntity.getRoles());
        userDTO.setFullName(userEntity.getFullName());
        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
//        userEntity.setId(userDTO.getId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setStatus(userDTO.getStatus());
        userEntity.setRoles(userDTO.getRoleIds());
        userEntity.setFullName(userDTO.getFullName());
        return userEntity;
    }
}
