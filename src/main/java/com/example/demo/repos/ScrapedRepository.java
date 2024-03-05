package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Scraped;

public interface ScrapedRepository extends CrudRepository<Scraped, Long> {

	boolean existsScrapedByDescription(String description); 
 
}
