/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.POETypeEntity;
import com.aelion.suivi.repositories.POETypeRepository;

/**
 * @author Aelion
 *
 */
@Service
public class POETypeService implements ICrud<POETypeEntity> {
	
	@Autowired
	private POETypeRepository repository;

	@Override
	public POETypeEntity add(POETypeEntity poeType) {
		return repository.save(poeType);
	}

	@Override
	public POETypeEntity[] add(POETypeEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(POETypeEntity poeType) {
		this.repository.save(poeType);
		
	}

	@Override
	public void delete(POETypeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<POETypeEntity> findAll() {
		return (List<POETypeEntity>) this.repository.findAll();
	}

	@Override
	public Optional<POETypeEntity> findOne(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	

}
