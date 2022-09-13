package com.aelion.suivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.services.InternEvaluationService;

@RestController
@RequestMapping ("/")
public class InternEvaluationController {
	
	@Autowired
	private InternEvaluationService service;
	
	
	

}
