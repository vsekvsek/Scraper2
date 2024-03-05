package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.States;

public interface StateRepository extends CrudRepository<States, Long> {  
   
	States findStatesByCityIs(String aCurrentCity);

}
