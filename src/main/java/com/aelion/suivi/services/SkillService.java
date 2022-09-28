/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.SkillEntity;
import com.aelion.suivi.repositories.SkillRepository;

/**
 * @author Aelion
 *
 */
@Service
public class SkillService implements ICrud<SkillEntity> {
	
	@Autowired
	private SkillRepository repository;

	@Override
	public SkillEntity add(SkillEntity skill) {
		//return this.repository.save(SkillEntity skill);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SkillEntity[] add(SkillEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SkillEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SkillEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SkillEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SkillEntity> findOne(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
