/**
 * 
 */
package com.aelion.suivi.services.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Aelion
 *
 */
public class JwtTokenMalformedException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;
	
	public JwtTokenMalformedException(String error) {
		super(error);
	}

}
