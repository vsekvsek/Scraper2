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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Scraped;
import com.example.demo.service.ScrapedServiceInterface;

@RestController
@RequestMapping("/api/scraped")
public class ScrapedController {
	
	@Autowired
	ScrapedServiceInterface service;
	@GetMapping("/all")
	public ResponseEntity<List<Scraped>> findAllUsers() {
		return new ResponseEntity<List<Scraped>>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Scraped> saveData(@RequestBody Scraped scraped) {
		Scraped saved = service.save(scraped);
		return new ResponseEntity<Scraped>(saved, HttpStatus.CREATED);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Scraped> findDataById(@PathVariable(value = "id") long id) {
		Optional<Scraped> scraped = service.findById(id);

		if (scraped.isPresent()) {
			return ResponseEntity.ok().body(scraped.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<Void> deleteById(@PathVariable("empid") Long idL) {
		Optional<Scraped> user = service.findById(idL);

		if (user.isPresent()) {
			service.deleteById(idL);
		}
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Void> deleteAll() {
		service.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/scrapeAll")
	public void scrapeIt() {
		service.scrape();
	}
	
	@GetMapping("/getAllScraped")
	public ResponseEntity<List<Scraped>> getAllScraped() {
		 List<Scraped> scraped = service.findAll();

		if (!(scraped.isEmpty())) {
			return ResponseEntity.ok().body(scraped);
		} else {
			return (ResponseEntity<List<Scraped>>) ResponseEntity.notFound();
		}
	}
}
