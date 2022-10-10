/**
 * 
 */
package com.aelion.suivi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aelion
 *
 */
@Entity
@Table(name="userRole")
public class UserRoleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String role;
/*	private UserEntity user;*/
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
	/*public UserEntity getUser() {
		return user;
	}*/
	/**
	 * @param user the user to set
	 */
	/*public void setUser(UserEntity user) {
		this.user = user;
	}*/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	
}
