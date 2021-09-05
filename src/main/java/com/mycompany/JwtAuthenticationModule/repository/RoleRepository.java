package com.mycompany.JwtAuthenticationModule.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.JwtAuthenticationModule.entity.RoleEntity;

@Repository
public interface  RoleRepository extends JpaRepository<RoleEntity,Long> {

	
}
