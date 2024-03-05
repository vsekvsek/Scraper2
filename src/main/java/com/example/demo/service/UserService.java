package com.example.demo.service;
 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repos.UserRepository;
 

@Service
public class UserService implements UserServiceInterface{

	@Autowired 
	UserRepository userRepo;
	@Override
	public List<User> findAll() {
		return (List<User>) userRepo.findAll();
	}

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public Optional<User> findById(long id) {
		
		return userRepo.findById(id);
	}

	@Override
	public void deleteById(long id) {
		
		 userRepo.deleteById(id);
	}

	 

}
