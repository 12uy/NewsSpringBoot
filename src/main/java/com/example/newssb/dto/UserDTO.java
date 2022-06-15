package com.example.newssb.dto;

import com.example.newssb.entity.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor

public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private List<RoleEntity> roleIds;
    private String fullName;
    private Integer status;

    public UserDTO(UserDTO userDTO){
        this.id = userDTO.getId();
        this.userName = userDTO.getUserName();
        this.password = userDTO.getPassword();
        this.roleIds = userDTO.getRoleIds();
        this.fullName = userDTO.getFullName();
        this.status = userDTO.getStatus();
    }

}
