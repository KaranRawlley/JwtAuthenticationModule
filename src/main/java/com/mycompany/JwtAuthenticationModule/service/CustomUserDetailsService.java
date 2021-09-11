package com.mycompany.JwtAuthenticationModule.service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.JwtAuthenticationModule.entity.RoleEntity;
import com.mycompany.JwtAuthenticationModule.entity.UserEntity;
import com.mycompany.JwtAuthenticationModule.model.RoleModel;
import com.mycompany.JwtAuthenticationModule.model.UserModel;
import com.mycompany.JwtAuthenticationModule.repository.RoleRepository;
import com.mycompany.JwtAuthenticationModule.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	public UserModel register(UserModel userModel) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userModel, userEntity);
		
		Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();
		for(RoleModel rm : userModel.getRoles() ) {
			Optional<RoleEntity> rs = roleRepository.findById(rm.getId());
			if(rs.isPresent()) {
				roleEntities.add(rs.get());
			}
		}
		userEntity.setRoles(roleEntities);
		userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
		userEntity = userRepo.save(userEntity);
		BeanUtils.copyProperties(userEntity, userModel);
		
		Set<RoleModel> roleModels = new HashSet<RoleModel>();
		RoleModel rm = null;
	
		for(RoleEntity rn : userEntity.getRoles()) {
			rm= new RoleModel();
			rm.setId(rn.getId());
			rm.setRoleName(rn.getRoleName());
			roleModels.add(rm);
		}
		
		userModel.setRoles(roleModels);
		return userModel;
		
	}
	
	//this method does the validation for user existence
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepo.findUserByUsername(username);
	
		if(userEntity == null) {//here Db call can be made instead of hardcoding. 
			throw new UsernameNotFoundException("User does not exists!");	
		}
		
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userEntity, userModel);
		Set<RoleModel> roleModels = new HashSet<RoleModel>();
		RoleModel rm = null;
	
		for(RoleEntity rn : userEntity.getRoles()) {
			rm= new RoleModel();
			rm.setId(rn.getId());
			rm.setRoleName(rn.getRoleName());
			roleModels.add(rm);
		}
		
		userModel.setRoles(roleModels);
		return userModel;
		
		 
	}

	
}
