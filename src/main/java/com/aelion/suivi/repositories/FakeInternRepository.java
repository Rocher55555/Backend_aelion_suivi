/**
 * 
 */
package com.aelion.suivi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aelion.suivi.entities.InternEntity;

/**
 * @author Aelion
 *
 */
public final class FakeInternRepository {
	
	private List<InternEntity> interns = new ArrayList<>();
	
	//constructor
	public FakeInternRepository() {
			this.populate();
	}   
	
	//getter
	public List<InternEntity> getInterns() {
		return this.interns;
	}
	
	//méthode add
	public InternEntity add(InternEntity intern) {
		this.interns.add(intern);
		return intern;
	}
	
	//méthode delete
	public void delete(InternEntity intern) {
		this.interns.remove(intern);
	}
	
	// Exo créer un findOne qui return un intern
	/*public InternEntity findOne(Long id) {
		for(InternEntity intern : this.interns) {
			if (intern.getId()==id) {
				return intern;
			}
		}
		return null;
	}
	*/
	
	//findOne avec le optional
	public Optional<InternEntity> findOne(Long id){
		for (InternEntity intern : this.interns) {
			if (intern.getId() == id) {
				return Optional.of(intern);
			}
		}
		return Optional.empty();
	}
	
	
	
	//méthode similaire au setter
	private void populate() {
		InternEntity intern = new InternEntity();
		//const intern: InternEntity = new internEntity()
		intern.setId(1L);                    // valeur 1 de type Long
		intern.setName("Aubert");
		intern.setFirstname("Jean-Luc");
		intern.setEmail("jla.web@gmail.com");
		intern.setPhoneNumber("+(33)6 23 05 88 88");
		
		this.interns.add(intern);    /// defini ma list intern setter()
		
		intern = new InternEntity();
		intern.setId(2L);                    
		intern.setName("Bond");
		intern.setFirstname("James");
		intern.setEmail("Bondj.web@hotmail.fr");
		intern.setPhoneNumber("+(33)7 07 07 07 07");
		
		this.interns.add(intern);			
	}

}
