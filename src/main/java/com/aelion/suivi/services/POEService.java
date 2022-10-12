/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.dto.POEShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.POERepository;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Aelion
 *
 */
@Service
public class POEService implements ICrud<POEEntity> {
	
	@Autowired
	private POERepository repository;
	

	@Override
	public POEEntity add(POEEntity poe) {
		return this.repository.save(poe);

	}

	@Override
	public POEEntity[] add(POEEntity[] poes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(POEEntity poe) {
		this.repository.save(poe);
	}

	//delete()
	//overload => celle que je vais utiliser
	public void delete(Long id) throws Exception {
		Optional<POEEntity> oEntity = this.findOne(id);
		if(oEntity.isPresent()) {
		try {
			this.repository.deleteById(id.intValue());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
			}
		} else {
		throw new Exception("POE with id : " + id + " was not found!");
		}
		//this.repository.deleteById(id);
	}
	
	@Override
	public List<POEEntity> findAll() {
		return (List<POEEntity>) this.repository.findAll();
	}

	//findOne
	@Override
	public Optional<POEEntity> findOne(Long id) {
		return this.repository.findById(id.intValue());
	}
	
	//get One pour faire differenment
	public POEEntity getOne(Long id) throws Exception {   // foit retourner un POEEntity
		Optional<POEEntity> oEntity = this.repository.findById(id.intValue());
		
		if (oEntity.isPresent()) {
			return oEntity.get();    //je return le poeentity presnt dans optione
		} else {
			throw new NotFoundException("the POE with " + id + " not found");
		}
	}

	
	// présente seulement pour respecter Icrud/ pour supprimer l'intern?
	@Override
	public void delete(POEEntity t) {
	
	}
	
	
	/**
	 * 
	 * @return a List of poeShortListDto objects
	 */
	public List<POEShortListDto> shortList(){
		ArrayList<POEEntity> itEntity = (ArrayList<POEEntity>) this.repository.findAll();
		//Need to map InternEntity to InternShortListDto
		ArrayList<POEShortListDto> dto = new ArrayList<>();
		for(POEEntity entity : itEntity) {
			POEShortListDto transformed = new POEShortListDto();
			dto.add(transformed.transform(entity));
		}
		return dto;
		
		// for (const entity : InternEntity of itEntity)
	}



}
