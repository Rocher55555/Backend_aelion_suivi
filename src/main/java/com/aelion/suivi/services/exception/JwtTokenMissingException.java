/**
 * 
 */
package com.aelion.suivi.services.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Aelion
 *
 */
public class JwtTokenMissingException extends AuthenticationException {

private static final long serialVersionUID = 1L;
	
	public JwtTokenMissingException(String error) {
		super(error);
	}
}
