package com.example.springbootPostgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootPostgresql.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}