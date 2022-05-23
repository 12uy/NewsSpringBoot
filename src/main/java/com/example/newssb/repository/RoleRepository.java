package com.example.newssb.repository;

import com.example.newssb.dto.RoleDTO;
import com.example.newssb.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}

