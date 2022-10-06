package com.aelion.suivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.services.UserService;

@RequestMapping ("/user")
@RestController
public class UserControler {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{email}")
	@CrossOrigin
	public ResponseEntity checkEmail(@PathVariable() String email) {
		//System.out.println("Reçu :" + email);
		return this.userService.checkEmail(email);
	}
}
