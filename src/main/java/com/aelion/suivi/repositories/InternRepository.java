package com.aelion.suivi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aelion.suivi.entities.InternEntity;

public interface InternRepository extends CrudRepository<InternEntity, Long> {
	
	
	
	
	/*
	
	DELETE FROM intern WHERE id = intern.id;  //delete()
	SELECT * FROM intern;  //findAll() 
	SELECT * FROM intern WHERE intern.id = 1;  //finOne()
	
	INSERT INTO 
				(name, firstname, birth_date, email, phone_number, address)
			VALUES
				('Tartempion', 'Jean', '1989-10-22', 'tt@gmail.com', '062524555' '05 chemin de la vallée' 
				
	UPDATE intern SET name = 'Van Dame', firstname='Jean_Claude', 
		birth_date='1963-04-15', email='jcvd@aware.com, phone_number='06', 
		address='Anvers' WHERE id=4;
		
	*/
	
	public List<InternEntity> findByName(String name);
	public List<InternEntity>findByFirstname(String firstname);
	
	/**
	 * JPQL
	 * ON travaille avec les attributs et les entités (class et attribut)
	 * @param email
	 * @return
	 */
	@Query("SELECT i FROM InternEntity i WHERE i.email= :email")              
	public InternEntity internByMail(@Param("email")String email);
	
	/**
	 * Native : on travaille avec
	 * ON travaille avec le nom des tables et les colonnes table(sql)
	 * @param email
	 * @return
	 */
	@Query(value="SELECT * FROM Intern WHERE email = :email", nativeQuery=true)              
	public InternEntity nativeinternByMail(@Param("email")String email);
	
	
	
}
