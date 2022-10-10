package com.aelion.suivi.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String role;
	@ManyToMany()
	private Set<UserAuthEntity> users;
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the user
	 */
	public Set<UserAuthEntity> getUsers() {
		return users;
	}
	/**
	 * @param user the user to set
	 */
	public void setUsers(Set<UserAuthEntity> user) {
		this.users = users;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}
