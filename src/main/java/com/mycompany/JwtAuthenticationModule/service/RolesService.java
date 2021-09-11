package com.mycompany.JwtAuthenticationModule.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.JwtAuthenticationModule.model.RoleModel;

public interface RolesService {

	
	public RoleModel createRole(RoleModel role);
	public List<RoleModel> getAllRoles();
	public RoleModel getRoleById(Long roleId);
	public void deleteRoleById(Long roleId);
}
