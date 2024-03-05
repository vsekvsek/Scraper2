package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.User;

public interface UserServiceInterface {

	List<User> findAll();

	User save(User user);

	Optional<User> findById(long id);

	void deleteById(long id);

}
