/**
 * 
 */
package com.aelion.suivi.helpers;

import java.util.List;

/**
 * @author Aelion
 *
 */
public class Response {
	
	private String token;
	private List<String> roles;
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public List<String> getRoles() {
		return this.roles;
	}
}
