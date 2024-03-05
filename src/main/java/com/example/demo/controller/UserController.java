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

import com.example.demo.entities.User;
import com.example.demo.service.UserServiceInterface;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserServiceInterface userService;

	@GetMapping("/all")
	public ResponseEntity<List<User>> findAllUsers() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
		Optional<User> user = userService.findById(id);

		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<Void> deleteById(@PathVariable("empid") Long empidL) {
		Optional<User> user = userService.findById(empidL);

		if (user.isPresent()) {
			userService.deleteById(empidL);
		}
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
