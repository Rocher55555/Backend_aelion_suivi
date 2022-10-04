package com.aelion.suivi.services;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.aelion.suivi.entities.UserEntity;

@Service
public class UserService {
	
	private ArrayList<UserEntity> list = new ArrayList<>();
	
	public UserService() {
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setEmail("jla.web@gmail.com");
		
		this.list.add(user);
	}
	public ResponseEntity checkEmail(String email) {
		System.out.println("Reçu :" + email);
		
		// Le vrai job commence ici ...trouver l'adresse mail passée en paramettre
		// dans la liste connue des users
		for (UserEntity user : this.list) {
			if(user.getEmail().equals(email)) {
				//ok,j'ai trouvé
				return ResponseEntity.ok(" user with" + email + " was found");
			}
		}
		return ResponseEntity.notFound().build();
	}

}
