package com.example.demo.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements; 

import com.example.demo.entities.Scraped;
import com.example.demo.entities.States;
import com.example.demo.repos.ScrapedRepository;
import com.example.demo.repos.StateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class ScrapedService implements ScrapedServiceInterface{
	
	private static final Logger logger = LogManager.getLogger(ScrapedService.class);
	@Autowired 
	ScrapedRepository repo;
	@Autowired 
	StateRepository stateRepo;
	
	@Override
	public List<Scraped> findAll() {
		return (List<Scraped>) repo.findAll();
	}

	@Override
	public Scraped save(Scraped scraped) {
		if(!repo.existsScrapedByDescription(scraped.getDescription())) {
			repo.save(scraped);
		}
		return scraped;
	}

	@Override
	public Optional<Scraped> findById(long id) {
		return repo.findById(id);
	}

	@Override
	public void deleteById(long id) {
		repo.deleteById(id);		
	}
	
	private void outputData(String aCurrentCity) throws IOException  {
		System.setProperty("http.proxyHost", "192.168.5.2");
		System.setProperty("http.proxyPort", "1080");
		Document doc = Jsoup.connect("https://" + aCurrentCity +".craigslist.org/d/motorcycles-scooters/search/mca").userAgent("Mozilla/5.0").get(); 
		Elements rows = doc.getAllElements().select("li");	 
		if(rows.isEmpty()) {			
			return;
		}    
		
		for(Element anElement : rows) { 
			if(isElementValid(anElement)) { 
				for(Element eachATag : anElement.getElementsByTag("a")) {
				Scraped  scrapedObject = new Scraped();
				Timestamp time = new Timestamp(System.currentTimeMillis());
				scrapedObject.setCity(aCurrentCity.toUpperCase());
				scrapedObject.setPrice(eachATag.getElementsByClass("price").text());
				scrapedObject.setDescription(eachATag.getElementsByClass("title").text());
				scrapedObject.setUrl(getHttps(eachATag.parentNode().childNodes()));
				scrapedObject.setDate(time.toString());		
				scrapedObject.setState(getState(aCurrentCity.toUpperCase()));
				/*
				 * Document pict =
				 * Jsoup.connect(getHttps(eachATag.parentNode().childNodes())).userAgent(
				 * "Mozilla/17.0").get(); Elements images = pict.select("img");
				 * if(images.size()>0) { String image = images.get(0).attributes().get("src");
				 * 
				 * }
				 */
				 
				save(scrapedObject);
				}
			}
			  
		} 
		logger.info("Currently saving " + aCurrentCity );
	}
	
	private String getState(String aCurrentCity) {
		return stateRepo.findStatesByCityIs(aCurrentCity).getState(); 
	}

	private boolean isElementValid(Element anElement) {
		return (anElement.getElementsByTag("a") != null) 
				&& (anElement.getElementsByTag("a").size() > 0)
				&& !(anElement.hasClass("cl-static-hub-links"))
				&& !(anElement.getElementsByTag("a").first().text().isEmpty());
	}
	 
	private String getHttps(List<Node> childNodes) {
		if(childNodes.isEmpty()) {
			return "";
		}
		for(Node each : childNodes){
			if(each.outerHtml().contains("https")){
				return each.attributes().asList().get(0).getValue();
			}
		}
		return "";
	}
	public void scrape() {
		List<States> states = (List<States>) this.stateRepo.findAll();
		for(States eachState : states){
			if(eachState.getCity() != null) {
			try {
				outputData(eachState.getCity());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}

	@Override
	public void deleteAll() {
		 this.repo.deleteAll();
	}
}
