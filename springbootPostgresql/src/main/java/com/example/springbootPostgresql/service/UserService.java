package com.example.springbootPostgresql.service;

import java.util.List;

import com.example.springbootPostgresql.entity.UserEntity;

public interface UserService {

    List<UserEntity> getAllUser();

    UserEntity getUserById(Long userId);

    void saveUser(UserEntity user);

    void updateUser(UserEntity user, Long userid);

    void deleteUseryId(Long userId);
    
}