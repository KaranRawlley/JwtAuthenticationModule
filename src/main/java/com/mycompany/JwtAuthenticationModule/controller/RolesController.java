package com.mycompany.JwtAuthenticationModule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.JwtAuthenticationModule.entity.RoleEntity;
import com.mycompany.JwtAuthenticationModule.model.RoleModel;
import com.mycompany.JwtAuthenticationModule.service.RolesService;

@RestController
@RequestMapping("/api/role")
public class RolesController {

	@Autowired
	private RolesService roleService;
	
	@PostMapping("/roles")
	public RoleModel createRole(@RequestBody RoleModel role) {
		System.out.println(role.getRoleName());
		return roleService.createRole(role); 
	}
	
	@GetMapping("/roles")
	public List<RoleModel> getAllRoles(){
		return roleService.getAllRoles();
	}
}
