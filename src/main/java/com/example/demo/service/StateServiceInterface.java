package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.States;

public interface StateServiceInterface {
	List<States> findAll();

	States save(States aState);

	Optional<States> findById(long id);

	void deleteById(long id);
}
