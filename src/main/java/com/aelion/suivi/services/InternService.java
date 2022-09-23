/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.repositories.InternRepository;

/**
 * @author Aelion
 *
 */
@Service
public class InternService implements ICrud<InternEntity> {
	
	//private FakeInternRepository internRepository = new FakeInternRepository();

	@Autowired
	private InternRepository repository;
	
	/**
	 * INSERT INNTO intern (name, firstname, ..., address) VALUES(...);
	 */
	@Override
	public InternEntity add(InternEntity intern) {
		return this.repository.save(intern);
	}

	@Override
	public InternEntity[] add(InternEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(InternEntity intern) {
		this.repository.save(intern);
		
	}

	@Override
	public void delete(InternEntity intern) {
		this.repository.delete(intern);
	}

	@Override
	public List<InternEntity> findAll() {		
		return (List<InternEntity>) this.repository.findAll();
	}

	@Override
	public Optional<InternEntity> findOne(Long id) {
		return this.repository.findById(id);
	}
	
	/**
	 * 
	 * @return a List of internShortListDto objects
	 */
	public List<InternShortListDto> shortList(){
		ArrayList<InternEntity> itEntity = (ArrayList<InternEntity>) this.repository.findAll();
		//Need to map InternEntity to InternShortListDto
		ArrayList<InternShortListDto> dto = new ArrayList<>();
		for(InternEntity entity : itEntity) {
			InternShortListDto transformed = new InternShortListDto();
			dto.add(transformed.transform(entity));
		}
		return dto;
		
		// for (const entity : InternEntity of itEntity)
	}

	
	//methode qui retourne une list pour tester
	public List<InternEntity> findByName(String name){
		return this.repository.findByName(name);
	}
	

	
	public List<InternEntity> findByFirstname(String firstname){
		return this.repository.findByFirstname(firstname);
	}
	
	/*
	public Optional<InternEntity> internByMail(String email) {
		return Optional.ofNullable(this.repository.internByMail(email));
	}
	*/
	
	/* FAUX DA CANCELLARE
	public ResponseEntity<?> internByMail(String email) {
		Optional<InternEntity> oInternEntity = this.repository.internByMail(email);
		if(oInternEntity.isPresent()) {
			return ResponseEntity.ok(oInternEntity.get());
		}
		return (ResponseEntity<?>)ResponseEntity.notFound().build();	
		}
	*/
	
	public ResponseEntity<?> internByMail(String email) {
		InternEntity entity = this.repository.internByMail(email);
		if(entity == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity);	
		}

	

	
	}
	
