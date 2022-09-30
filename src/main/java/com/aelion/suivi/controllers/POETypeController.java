/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.entities.POETypeEntity;
import com.aelion.suivi.services.POETypeService;

/**
 * @author Aelion
 *
 */
@RestController
@RequestMapping("/poetype")
public class POETypeController {
	
	@Autowired
	private POETypeService service;

	
	@GetMapping()
	public List<POETypeEntity> findAll(){
		return this.service.findAll();
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody POETypeEntity poeType) {
		this.service.update(poeType);
		return ResponseEntity.noContent().build();
	}
	
	

}
