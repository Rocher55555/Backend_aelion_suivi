/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.services.InternService;

/**
 * @author Aelion
 *
 */

@RestController
@RequestMapping("/intern")
public class Intern {
	
	@Autowired
	private InternService internService;
	
	
	@GetMapping("/hello")
	@CrossOrigin()
	public ResponseEntity<String> greetings(){
		return ResponseEntity.ok("Hello springBoot");
	}
	
	
	@GetMapping()
	@CrossOrigin()
	public List<InternEntity> findAll() {
		return this.internService.findAll();
	}
	
	
	@GetMapping("/{id}")
	@CrossOrigin()
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Optional<InternEntity> oInternEntity= this.internService.findOne(id);
		if(oInternEntity.isPresent()) {
			return ResponseEntity.ok(oInternEntity.get());
		}
		return (ResponseEntity<?>) ResponseEntity.notFound().build();
	}
	
	/**
	 * POST METTRE A JOUR(ajouter)
	 * @param intern
	 * @return http://status  (201)
	 */
	@PostMapping()
	@CrossOrigin()
	public InternEntity add(@RequestBody InternEntity intern) {
		return this.internService.add(intern);
	}
	
	/**
	 * @param 
	 * @return http://status  (204)
	 */
	@DeleteMapping()
	@CrossOrigin
	public ResponseEntity<?> delete(@RequestBody InternEntity intern) {
		this.internService.delete(intern);
		return ResponseEntity.noContent().build();
//no.content renvoi un status 204	
	}
	
	/**
	 * POST METTRE A JOUR(update)
	 * @param intern
	 * @return http://status  (201)
	 */
	@PutMapping()
	@CrossOrigin()
	public ResponseEntity<?> update(@RequestBody InternEntity intern) {
		this.internService.update(intern);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/shortlist")
	@CrossOrigin()
	public List<InternShortListDto>shortList(){
		return this.internService.shortList();
	}
	
	@GetMapping("/byname/{name}")
	@CrossOrigin()
	public List<InternEntity> findByName(@PathVariable String name){
		return this.internService.findByName(name);
	}
	
	
	
	
	@GetMapping("/byfirstname/{firstname}")
	@CrossOrigin()
	public List<InternEntity> findByFirstname(@PathVariable String firstname){
		return internService.findByFirstname(firstname);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
