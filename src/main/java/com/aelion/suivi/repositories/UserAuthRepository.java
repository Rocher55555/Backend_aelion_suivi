package com.aelion.suivi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aelion.suivi.entities.UserAuthEntity;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Long> {
	
	Optional<UserAuthEntity> findByUserName(String userName);
	
}
