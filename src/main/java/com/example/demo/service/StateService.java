package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.States;
import com.example.demo.repos.StateRepository;

@Service
public class StateService implements StateServiceInterface{

	@Autowired 
	StateRepository stateRepo;
	@Override
	public List<States> findAll() {
		return (List<States>) stateRepo.findAll();
	}

	@Override
	public States save(States aState) {
		return stateRepo.save(aState);
	}

	@Override
	public Optional<States> findById(long id) {
		return stateRepo.findById(id);
	}

	@Override
	public void deleteById(long id) {
		stateRepo.deleteById(id);
		
	}

}
