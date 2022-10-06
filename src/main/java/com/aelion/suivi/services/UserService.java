package com.aelion.suivi.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.aelion.suivi.entities.UserEntity;
import com.aelion.suivi.repositories.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repository;

	/**
	 * 
	 * @param email
	 * @return a Response (http response 200 or 404)
	 */
	public ResponseEntity checkEmail(String email) {
		//System.out.println("Service Reçu :" + email);
		UserEntity userEntity = this.repository.userByMail(email);
		if (userEntity == null) {
			return ResponseEntity.notFound().build(); 
		}
		return ResponseEntity.ok().build();
	}
}
		
		
	