package com.mobileapp.ws;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobileapp.ws.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository <UserEntity, Long> {
	
	UserEntity findByEmail(String email);

}
