package com.aelion.suivi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aelion.suivi.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	//String internByMail = "jean@hotmail.fr";

	/**
	 * Je travail avec l'entity userEntity et l'atribut mail
	 * @param email
	 * @return
	 */
	
	@Query("SELECT u FROM UserEntity u WHERE u.email = :email")
	public UserEntity userByMail(@Param("email") String email);
}
