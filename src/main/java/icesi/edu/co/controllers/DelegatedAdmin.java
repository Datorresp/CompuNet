package icesi.edu.co.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import icesi.edu.co.hr.Employee;
import icesi.edu.co.main.BasicInfo;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Person;
import icesi.edu.co.sales.Salestaxrate;


@Component
public class DelegatedAdmin {

	@Autowired
	private RestTemplate restTemplate;

	
	  //////////////////////////////////////////////////////////////////////////////////////
	 ////////////////////////////////Country Region////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	public Countryregion getCountry(int id) {
		
		String url = "http://localhost:8080/countries/" + id;
		Countryregion cr = restTemplate.getForObject(url, Countryregion.class);
		return cr;
	}
	
	public Iterable<Countryregion> getAllCountries(){
		
		String url = "http://localhost:8080/countries";
		Countryregion[] cr = restTemplate.getForObject(url, Countryregion[].class);
		return Arrays.asList(cr);
	}
	
	public String CreateCountry(Countryregion p) {
		
		String url = "http://localhost:8080/countries/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> entity = new HttpEntity<Countryregion>(p, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateCountry(int id, @Validated(BasicInfo.class) Countryregion p) {
		
		String url = "http://localhost:8080/countries/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> entity = new HttpEntity<Countryregion>(p, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
		
	}
	
	  //////////////////////////////////////////////////////////////////////////////////////
	 ////////////////////////////////Sales Tax Rate////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	
	public Salestaxrate getSales(int id) {
		
		String url = "http://localhost:8080/sales/" + id;
		Salestaxrate sr = restTemplate.getForObject(url, Salestaxrate.class);
		return sr;
	}
	
	public Iterable<Salestaxrate> getAllSales(){
		
		String url = "http://localhost:8080/sales/";
		Salestaxrate[] sr = restTemplate.getForObject(url, Salestaxrate[].class);
		
		return Arrays.asList(sr);	
	}
	
	public String CreateSales(Salestaxrate p) {
		
		String url = "http://localhost:8080/sales/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> entity = new HttpEntity<Salestaxrate>(p, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateSales(int id,  @Validated(BasicInfo.class) Salestaxrate p) {
		
		String url = "http://localhost:8080/sales/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> entity = new HttpEntity<Salestaxrate>(p, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
		
	}
	
	  //////////////////////////////////////////////////////////////////////////////////////
	 ////////////////////////////////Employee//////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	public Employee getEmployee(Integer id) {
		String url = "http://localhost:8080/employees/" + id;
		Employee p = restTemplate.getForObject(url, Employee.class);
		return p;
	}
	
	
	public Iterable<Employee> getAllEmployee(){
		String url = "http://localhost:8080/employees";
		Employee[] p = restTemplate.getForObject(url, Employee[].class);
		return Arrays.asList(p);
	}
	
	
	public String createEmploye(Employee cr) {
		String url = "http://localhost:8080/employees/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(cr, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	public String updateEmployee(long id, @Validated(BasicInfo.class) Employee cr) {
		String url = "http://localhost:8080/employees/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(cr, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	
	  //////////////////////////////////////////////////////////////////////////////////////
	 ////////////////////////////////Person////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	public Person getPerson(Integer id) {
		String url = "http://localhost:8080/persons/" + id;
		Person p = restTemplate.getForObject(url, Person.class);
		return p;
	}
	
	
	public Iterable<Person> getAllPersons(){
		String url = "http://localhost:8080/persons";
		Person[] p = restTemplate.getForObject(url, Person[].class);
		return Arrays.asList(p);
	}
	
	
	public String createPerson(Person cr) {
		String url = "http://localhost:8080/persons/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(cr, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	public String updatePerson(long id, @Validated(BasicInfo.class) Person cr) {
		String url = "http://localhost:8080/persons/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(cr, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	

	
	
	
}
