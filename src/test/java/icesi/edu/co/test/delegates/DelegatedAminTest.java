package icesi.edu.co.test.delegates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import icesi.edu.co.controllers.DelegatedAdmin;
import icesi.edu.co.hr.Employee;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Person;
import icesi.edu.co.sales.Salestaxrate;

class DelegatedAminTest {

	private final static String URL_COUNT = "http://localhost:8080/countries";
	private final static String URL_SALES = "http://localhost:8080/sales";
	private final static String URL_PERSON = "http://localhost:8080/persons";
	private final static String URL_EMP = "http://localhost:8080/employees";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private DelegatedAdmin delegatedAdmin;

	// ---------------------Objects for Tests---------------------
	Countryregion country;

	Salestaxrate salestaxrate;

	Employee employee;

	Person person;

	// ---------------------@BeforeEach Test---------------------

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		delegatedAdmin = new DelegatedAdmin();
		delegatedAdmin.setRestTemplate(restTemplate);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Country Region Test///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////////////////////

	@Test
	void getCountryTest() {
		country = new Countryregion();
		country.setName("Colombia");
		country.setCountryregioncode("COL");
		country.setCountryregionid(15);
		when(restTemplate.getForObject(URL_COUNT + "/" + country.getCountryregionid(), Countryregion.class))
				.thenReturn(country);
		assertEquals(delegatedAdmin.getCountry(15).getCountryregioncode(), "COL");
	}

	@Test
	void getAllCountryTest() {
		country = new Countryregion();
		Countryregion c1 = new Countryregion();
		Countryregion c2 = new Countryregion();
		Countryregion[] p = { c1, c2, country };
		List<Countryregion> allCountries = new ArrayList<>();

		when(restTemplate.getForObject(URL_COUNT, Countryregion[].class)).thenReturn(p);
		Iterable<Countryregion> get = delegatedAdmin.getAllCountries();
		get.forEach(allCountries::add);

		assertEquals(p.length, allCountries.size());
	}

	@Test
	void createCountryTest() {
		country = new Countryregion();
		country.setName("Colombia");
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> entity = new HttpEntity<Countryregion>(country, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_COUNT+"/", HttpMethod.POST, entity, String.class)).thenReturn(response);
		
		delegatedAdmin.CreateCountry(country);
		verify(restTemplate).exchange(URL_COUNT+"/", HttpMethod.POST, entity, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	void updateCountryTest() {
		country = new Countryregion();
		country.setName("Colombia");
		country.setCountryregionid(15);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> entity = new HttpEntity<Countryregion>(country, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_COUNT+"/"+country.getCountryregionid(), HttpMethod.PUT, entity, String.class)).thenReturn(response);
		delegatedAdmin.updateCountry(country.getCountryregionid(),country);
		verify(restTemplate).exchange(URL_COUNT+"/"+country.getCountryregionid(), HttpMethod.PUT, entity, String.class);
	}

	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Sales Tax Rate Test///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////////////////////

	@Test
	void getSalesTest() {
		salestaxrate = new Salestaxrate();
		salestaxrate.setName("Test");
		salestaxrate.setSalestaxrateid(10);
		when(restTemplate.getForObject(URL_SALES + "/" + salestaxrate.getSalestaxrateid(), Salestaxrate.class))
				.thenReturn(salestaxrate);
		assertEquals(delegatedAdmin.getSales(10).getName(), "Test");
	}

	@Test
	void getAllSalesTest() {
		salestaxrate = new Salestaxrate();
		Salestaxrate s1 = new Salestaxrate();
		Salestaxrate s2 = new Salestaxrate();
		Salestaxrate s3 = new Salestaxrate();
		Salestaxrate[] p = { s1, s2, s3, salestaxrate };
		List<Salestaxrate> allSales = new ArrayList<>();

		when(restTemplate.getForObject(URL_SALES+"/", Salestaxrate[].class)).thenReturn(p);
		Iterable<Salestaxrate> get = delegatedAdmin.getAllSales();
		get.forEach(allSales::add);

		assertEquals(p.length, allSales.size());
	}

	@Test
	void CreateSalesTest() {
		salestaxrate = new Salestaxrate();
		salestaxrate.setName("Test");
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> entity = new HttpEntity<Salestaxrate>(salestaxrate, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_SALES+"/", HttpMethod.POST, entity, String.class)).thenReturn(response);
		
		delegatedAdmin.CreateSales(salestaxrate);
		verify(restTemplate).exchange(URL_SALES+"/", HttpMethod.POST, entity, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void updateSalesTest() {
		salestaxrate = new Salestaxrate();
		salestaxrate.setName("Test");
		salestaxrate.setRowguid(8);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> entity = new HttpEntity<Salestaxrate>(salestaxrate, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_SALES+"/"+salestaxrate.getRowguid(), HttpMethod.PUT, entity, String.class)).thenReturn(response);
		delegatedAdmin.updateSales(salestaxrate.getRowguid(),salestaxrate);
		verify(restTemplate).exchange(URL_SALES+"/"+salestaxrate.getRowguid(), HttpMethod.PUT, entity, String.class);
	}

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Person Test/////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

	@Test
	void getPersonTest() {
		person = new Person();
		person.setFirstname("Carlos");
		person.setRowguid(5);
		when(restTemplate.getForObject(URL_PERSON + "/" + person.getRowguid(), Person.class))
				.thenReturn(person);
		assertEquals(delegatedAdmin.getPerson(5).getFirstname(), "Carlos");
	}

	@Test
	void getAllPersonTest() {
		person = new Person();
		Person s1 = new Person();
		Person s2 = new Person();
		Person[] p = { s1, s2, person };
		List<Person> allSales = new ArrayList<>();

		when(restTemplate.getForObject(URL_PERSON, Person[].class)).thenReturn(p);
		Iterable<Person> get = delegatedAdmin.getAllPersons();
		get.forEach(allSales::add);

		assertEquals(p.length, allSales.size());
	}

	@Test
	void CreatePersonTest() {
		person = new Person();
		person.setFirstname("Carlos");
		person.setRowguid(5);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_PERSON+"/", HttpMethod.POST, entity, String.class)).thenReturn(response);
		
		delegatedAdmin.createPerson(person);
		verify(restTemplate).exchange(URL_PERSON+"/", HttpMethod.POST, entity, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void updatePersonTest() {
		person = new Person();
		person.setFirstname("Carlos");
		person.setRowguid(5);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_PERSON+"/"+person.getRowguid(), HttpMethod.PUT, entity, String.class)).thenReturn(response);
		
		delegatedAdmin.updatePerson(person.getRowguid(), person);
		verify(restTemplate).exchange(URL_PERSON+"/"+person.getRowguid(), HttpMethod.PUT, entity, String.class);
	}

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Employee Test///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

	@Test
	void getEmployeeTest() {
		employee = new Employee();
		employee.setGender("MALE");
		employee.setRowguid(25);
		when(restTemplate.getForObject(URL_EMP + "/" + employee.getRowguid() ,Employee.class))
				.thenReturn(employee);
		assertEquals(delegatedAdmin.getEmployee(25).getGender(), "MALE");
	}

	@Test
	void getAllEmployeeTest() {
		employee = new Employee();
		Employee s1 = new Employee();
		Employee s2 = new Employee();
		Employee s3 = new Employee();
		Employee[] p = { s1, s2, s3, employee };
		List<Employee> allSales = new ArrayList<>();

		when(restTemplate.getForObject(URL_EMP, Employee[].class)).thenReturn(p);
		Iterable<Employee> get = delegatedAdmin.getAllEmployee();
		get.forEach(allSales::add);

		assertEquals(p.length, allSales.size());
	}

	@Test
	void CreateEmployeeTest() {
		employee = new Employee();
		employee.setGender("MALE");
		employee.setRowguid(25);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_EMP+"/", HttpMethod.POST, entity, String.class)).thenReturn(response);
		
		delegatedAdmin.createEmploye(employee);
		verify(restTemplate).exchange(URL_EMP+"/", HttpMethod.POST, entity, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void updateEmployeeTest() {
		employee = new Employee();
		employee.setGender("MALE");
		employee.setRowguid(25);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_EMP+"/"+employee.getRowguid(), HttpMethod.PUT, entity, String.class)).thenReturn(response);
		
		delegatedAdmin.updateEmployee(employee.getRowguid(), employee);
		verify(restTemplate).exchange(URL_EMP+"/"+employee.getRowguid(), HttpMethod.PUT, entity, String.class);
	}

}
