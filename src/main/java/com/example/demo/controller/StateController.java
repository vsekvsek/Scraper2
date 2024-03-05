package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.States; 
import com.example.demo.service.StateServiceInterface; 
@RestController
@RequestMapping("/api/states")
public class StateController {


	@Autowired
	StateServiceInterface stateService;

	@GetMapping("/all")
	public ResponseEntity<List<States>> findAllStates() {
		return new ResponseEntity<List<States>>(stateService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<States> saveUser(@RequestBody States state) {
		States saved = stateService.save(state);
		return new ResponseEntity<States>(saved, HttpStatus.CREATED);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<States> findStateById(@PathVariable(value = "id") long id) {
		Optional<States> state = stateService.findById(id);

		if (state.isPresent()) {
			return ResponseEntity.ok().body(state.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{stateid}")
	public ResponseEntity<Void> deleteById(@PathVariable("stateid") Long idL) {
		Optional<States> state = stateService.findById(idL);

		if (state.isPresent()) {
			stateService.deleteById(idL);
		}
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
