package com.CRUD.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CRUD.demo.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
}
