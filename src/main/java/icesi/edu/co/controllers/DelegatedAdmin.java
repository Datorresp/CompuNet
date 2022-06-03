package icesi.edu.co.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import icesi.edu.co.person.Countryregion;
import icesi.edu.co.sales.Salestaxrate;


@Component
public class DelegatedAdmin {

	@Autowired
	private RestTemplate restTemplate;

	
	public Countryregion getCountry(int id) {
		
		String url = "http://localhost:8080/country/" + id;
		Countryregion p = restTemplate.getForObject(url, Countryregion.class);
		return p;
	}
	
	public Iterable<Countryregion> getAllCountry(){
		
		String url = "http://localhost:8080/country";
		Countryregion[] p = restTemplate.getForObject(url, Countryregion[].class);
		
		return Arrays.asList(p);
	}
	
	public String CreateCountry(Countryregion p) {
		
		String url = "http://localhost:8080/country";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> p1 = new HttpEntity<Countryregion>(p, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, p1, String.class).getBody();
	}
	
	public String updateCountry(int id,  Countryregion p) {
		
		String url = "http://localhost:8080/country/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> p1 = new HttpEntity<Countryregion>(p, headers);
		
		return restTemplate.exchange(url, HttpMethod.PUT, p1, String.class).getBody();
		
	}
	
	  //////////////////////////////////////////////////////////////////////////////////////
	 ////////////////////////////////Sales Tax Rate////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	
	public Salestaxrate getSales(int id) {
		
		String url = "http://localhost:8080/sales/"+ id;
		Salestaxrate p = restTemplate.getForObject(url, Salestaxrate.class);
		return p;
	}
	
	public Iterable<Salestaxrate> getAllSales(){
		
		String url = "http://localhost:8080/sales";
		Salestaxrate[] p = restTemplate.getForObject(url, Salestaxrate[].class);
		
		return Arrays.asList(p);		
	}
	
	public String CreateSales(Salestaxrate p) {
		
		String url = "http://localhost:8080/sales";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> p1 = new HttpEntity<Salestaxrate>(p, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, p1, String.class).getBody();
	}
	
	public String updateSales(int id,  Salestaxrate p) {
		
		String url = "http://localhost:8080/sales/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> p1 = new HttpEntity<Salestaxrate>(p, headers);
		
		return restTemplate.exchange(url, HttpMethod.PUT, p1, String.class).getBody();
		
	}
	
	
	
}
