/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternInputDto;
import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.InternRepository;
import com.aelion.suivi.repositories.POERepository;

/**
 * @author Aelion
 *
 */
@Service
public class InternService implements ICrud<InternEntity> {
	
	//private FakeInternRepository internRepository = new FakeInternRepository();

	@Autowired
	private InternRepository repository;
	//injection la dépendance de poeRepository pour ce service
	@Autowired
	private POERepository poeRepository;
	
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
	
	/*public ResponseEntity<?> internByMail(String email) {
		InternEntity entity = this.repository.internByMail(email);
		
		if(entity == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity);	
		}*/
	
	public ResponseEntity<?> internByMail(String email) {
		ResponseEntity response = null;
		
		InternEntity entity = this.repository.internByMail(email);
		
		if(entity == null) {
			return new ResponseEntity(HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.FORBIDDEN);
		}


	
		public InternEntity addInternAndPoes(InternInputDto internDto) {
			InternEntity intern = new InternEntity();
			intern.setAddress(internDto.address); // deserialization
			intern.setBirthDate(internDto.birthDate);
			intern.setEmail(internDto.email);
			intern.setFirstname(internDto.firstname);
			intern.setName(internDto.name);
			intern.setPhoneNumber(internDto.phoneNumber);
			
			// Persists intern
			this.repository.save(intern);   // on faire pesister l'objet inter dans la BD
			
			// Persists POEs with the new Intern
			//je prends la list poes d'internDto , je la parcours
			internDto.poes.forEach(inputPoe -> {
				Optional<POEEntity> oPoe = this.poeRepository.findById(inputPoe.getId());
				//si poe cochée est trouvée via l'id de la BD
				if (oPoe.isPresent()) {
					POEEntity poe = oPoe.get();
					//...alors j'ajoute l'intern à la poe et je les 'save'
					poe.addIntern(intern);
					this.poeRepository.save(poe);
				}
			});
			return intern;
		}


	

	
	}
	
