package com.mycompany.JwtAuthenticationModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.JwtAuthenticationModule.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
