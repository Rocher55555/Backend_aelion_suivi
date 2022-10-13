/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
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
	
	/**
	 * Delete, firstly an intern present in a POE, and after recording the information, delete the intern
	 * Permit to remove the relationship with InternToPoe
	 * 
	 * @return a List of InternEntity
	 */
	@Override
	public void delete(InternEntity intern) {
		//get poes
		ArrayList<POEEntity> poes = (ArrayList<POEEntity>) this.poeRepository.findAll();
		for (POEEntity poe: poes) {
			List<InternEntity> interns = new ArrayList<>();
			poe.getInterns().forEach(internFromPoe -> {
				interns.add(internFromPoe);				
			});
			//work on the copy
			for(InternEntity internFromPoe: interns) {
				if ( internFromPoe.getId() == intern.getId()) {
					// delete on the original : poe!
					poe.deleteIntern(internFromPoe);
					this.poeRepository.save(poe);
				}
			}
		}
		//after POE's loop
		this.repository.delete(intern);
	}

	
	/**
	 * Find All Intern
	 * @return a List of InternEntity
	 */
	@Override
	public List<InternEntity> findAll() {		
		return (List<InternEntity>) this.repository.findAll();
	}

	
	/**
	 * Find One Intern
	 * @return an InternEntity
	 */
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
			this.repository.save(intern);                 // on faire persister l'objet inter dans la BD
			
			// Persists POEs with the new Intern
			//je prends la list poes d'internDto , je la parcours
			internDto.poes.forEach(inputPoe -> {
				Optional<POEEntity> oPoe = this.poeRepository.findById(inputPoe.getId());
				//si poe cochéee est trouvée via l'id de la BD
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
	
