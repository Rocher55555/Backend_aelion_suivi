package com.aelion.suivi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aelion.suivi.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
