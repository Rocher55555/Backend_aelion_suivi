/**
 * 
 */
package com.aelion.suivi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aelion.suivi.entities.UserEntity;

/**
 * @author Aelion
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUserName(String userName);
	
	@Query("SELECT u FROM UserEntity u WHERE u.userName= :userName")
	UserEntity userByMail(String userName);

	@Query("SELECT u FROM UserEntity u WHERE u.userName= :userName")
	UserEntity userByName(String userName);

}
