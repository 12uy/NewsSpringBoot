package com.example.newssb.converter;

import com.example.newssb.dto.RoleDTO;
import com.example.newssb.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDTO toDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setCode(roleEntity.getCode());
        roleDTO.setName(roleEntity.getName());
        return roleDTO;
    }

    public RoleEntity toEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDTO.getId());
        roleEntity.setCode(roleDTO.getCode());
        roleEntity.setName(roleDTO.getName());
        return roleEntity;
    }
}
