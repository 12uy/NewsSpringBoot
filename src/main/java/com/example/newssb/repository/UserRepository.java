package com.example.newssb.repository;

import com.example.newssb.dto.UserDTO;
import com.example.newssb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDTO findOneByUserNameAndStatus(String userName, int status);
}
