package com.aelion.suivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aelion.suivi.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
