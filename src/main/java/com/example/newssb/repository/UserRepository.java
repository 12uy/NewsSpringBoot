package com.example.newssb.repository;

import com.example.newssb.dto.UserDTO;
import com.example.newssb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findOneByUserName(String userName);
}
