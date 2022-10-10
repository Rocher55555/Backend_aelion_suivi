/**
 * 
 */
package com.aelion.suivi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aelion.suivi.entities.UserEntity;

/**
 * @author Aelion
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUserName(String userName);

}
