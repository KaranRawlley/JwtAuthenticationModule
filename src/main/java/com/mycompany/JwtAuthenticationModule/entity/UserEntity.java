package com.mycompany.JwtAuthenticationModule.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER_INFO_MST")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column( name = "USER_NAME",nullable = false)
	private String username;
	
	@Column( name = "PASSWORD", nullable = false)
	private String password;
	
	@Column( name = "FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column( name = "LAST_NAME")
	private String lastName;
	
	@Column( name = "EMAIL", nullable = false)
	private String email;
	
	@Column( name = "PHONE_NO")
	private String phoneNo;
	
	@JsonManagedReference //To avoid circular dependecy in bi-directional mapping
	@ManyToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",  
	joinColumns = {@JoinColumn(name= "user_id" )},
	inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<RoleEntity> roles = new HashSet<>();
	
	
	
	
	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	

}
