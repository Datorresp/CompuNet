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

import icesi.edu.co.controllers.DelegatedUser;
import icesi.edu.co.person.Address;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Stateprovince;

class DelegatedUserTest {

	private final static String URL_PROV = "http://localhost:8080/provinces";
	private final static String URL_ADDRESS = "http://localhost:8080/addresses";
	private final static String URL_COUNT = "http://localhost:8080/countries";

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	DelegatedUser delegatedUser;

	// ---------------------Objects for Tests---------------------

	Stateprovince stateprovince;

	Address address;

	Countryregion country;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		delegatedUser = new DelegatedUser();
		delegatedUser.setRestTemplate(restTemplate);
	}

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// State Province Test///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////////////////////
	

	@Test
	void getStateProvinceTest() {
		stateprovince = new Stateprovince();
		stateprovince.setRowguid(20);
		stateprovince.setTerritoryid(1503);
		when(restTemplate.getForObject(URL_PROV + "/" + stateprovince.getRowguid(), Stateprovince.class)).thenReturn(stateprovince);
		assertEquals(delegatedUser.getStateprovince(20).getTerritoryid(), 1503);
	}

	@Test
	void getAllStateProvinceTest() {
		stateprovince = new Stateprovince();
		Stateprovince c1 = new Stateprovince();
		Stateprovince c2 = new Stateprovince();
		Stateprovince[] p = { c1, c2, stateprovince };
		List<Stateprovince> allStateProvince = new ArrayList<>();

		when(restTemplate.getForObject(URL_PROV, Stateprovince[].class)).thenReturn(p);
		Iterable<Stateprovince> get = delegatedUser.getAllStateprovinces();
		get.forEach(allStateProvince::add);

		
		assertEquals(p.length, allStateProvince.size());
	}

	@Test
	void createStateProvinceTest() {
		stateprovince = new Stateprovince();
		stateprovince.setRowguid(20);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(stateprovince, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_PROV, HttpMethod.POST, entity, String.class)).thenReturn(response);
		
		delegatedUser.createStateprovince(stateprovince);
		verify(restTemplate).exchange(URL_PROV, HttpMethod.POST, entity, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	void updateStateProvinceTest() {
		stateprovince = new Stateprovince();
		stateprovince.setRowguid(20);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(stateprovince, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_PROV+"/"+stateprovince.getRowguid(), HttpMethod.PUT, entity, String.class)).thenReturn(response);
		
		delegatedUser.updateStateprovince(stateprovince.getRowguid(), stateprovince);
		verify(restTemplate).exchange(URL_PROV+"/"+stateprovince.getRowguid(), HttpMethod.PUT, entity, String.class);
	}

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////// Address Test/////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////////////////////
	

	@Test
	void getAddressTest() {
		address = new Address();
		address.setRowguid(20);
		address.setCity("Cali");
		when(restTemplate.getForObject(URL_ADDRESS + "/" + address.getRowguid(), Address.class)).thenReturn(address);
		assertEquals(delegatedUser.getAddress(20).getCity(), "Cali");
	}

	@Test
	void getAllAddressesTest() {
		address = new Address();
		Address a1 = new Address();
		Address a2 = new Address();
		Address[] p = { a1, a2, address };
		List<Address> allAddress = new ArrayList<>();

		when(restTemplate.getForObject(URL_ADDRESS, Address[].class)).thenReturn(p);
		Iterable<Address> get = delegatedUser.getAllAddresses();
		get.forEach(allAddress::add);

		
		assertEquals(p.length, allAddress.size());
	}

	@Test
	void createAddressTest() {
		address = new Address();
		address.setRowguid(20);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Address> entity = new HttpEntity<Address>(address, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_ADDRESS, HttpMethod.POST, entity, String.class)).thenReturn(response);
		
		delegatedUser.createAddress(address);
		verify(restTemplate).exchange(URL_ADDRESS, HttpMethod.POST, entity, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void updateAddressTest() {
		address = new Address();
		address.setRowguid(20);
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Address> entity = new HttpEntity<Address>(address, headers);
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.CREATED);
		when(restTemplate.exchange(URL_ADDRESS+"/"+address.getRowguid(), HttpMethod.PUT, entity, String.class)).thenReturn(response);
		
		delegatedUser.updateAddress(address.getRowguid(), address);
		verify(restTemplate).exchange(URL_ADDRESS+"/"+address.getRowguid(), HttpMethod.PUT, entity, String.class);
	}

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Country Region Test///////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////////////////////
	

	@Test
	void getAllCountryRegionTest() {
		country = new Countryregion();
		Countryregion c1 = new Countryregion();
		Countryregion c2 = new Countryregion();
		Countryregion[] p = { c1, c2, country };
		List<Countryregion> allCountries = new ArrayList<>();

		when(restTemplate.getForObject(URL_COUNT, Countryregion[].class)).thenReturn(p);
		Iterable<Countryregion> get = delegatedUser.getAllCountryregion();
		get.forEach(allCountries::add);

		assertEquals(p.length, allCountries.size());
	}

}
