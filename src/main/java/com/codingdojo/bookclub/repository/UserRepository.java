package com.codingdojo.bookclub.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bookclub.models.User;


@Repository
public interface UserRepository extends CrudRepository <User, Long>{
	
	
	Optional<User> findByEmail(String email);
	
	
	Optional<User> findById(Long id);
}

