package com.mycompany.JwtAuthenticationModule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.JwtAuthenticationModule.entity.RoleEntity;
import com.mycompany.JwtAuthenticationModule.model.RoleModel;
import com.mycompany.JwtAuthenticationModule.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RolesService{
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public RoleModel createRole(RoleModel role) {
		
		RoleEntity roleEntity = new RoleEntity();
		BeanUtils.copyProperties(role, roleEntity);
		roleEntity = roleRepo.save(roleEntity);
		BeanUtils.copyProperties(roleEntity, role);
		
		return role;
	}

	@Override
	public List<RoleModel> getAllRoles() {
		List<RoleEntity> roleEntities = roleRepo.findAll();
		List<RoleModel> roleModels = new ArrayList<>();
		RoleModel rs =null;
		for( RoleEntity re: roleEntities) {
		    rs = new RoleModel();
			BeanUtils.copyProperties(re, rs);
			roleModels.add(rs);
		}
		
		return roleModels;
	}

	@Override
	public RoleModel getRoleById(Long roleId) {
		RoleModel roleModel = new RoleModel();
		RoleEntity roleEntity = roleRepo.findById(roleId).get();
		
		BeanUtils.copyProperties(roleEntity, roleModel);
		
		return roleModel;
	}

	@Override
	public void deleteRoleById(Long roleId) {
		roleRepo.deleteById(roleId);
		
	}

	
}
