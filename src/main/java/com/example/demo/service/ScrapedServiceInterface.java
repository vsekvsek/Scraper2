package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Scraped;
public interface ScrapedServiceInterface{

	void deleteById(long id);

	List<Scraped> findAll();

	Optional<Scraped> findById(long id);

	Scraped save(Scraped scraped);
	
	void scrape();

	void deleteAll();
	

}
