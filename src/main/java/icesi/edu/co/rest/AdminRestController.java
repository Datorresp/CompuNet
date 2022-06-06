package icesi.edu.co.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import icesi.edu.co.DAOService.AddressDAOService;
import icesi.edu.co.DAOService.CountryRegionDAOService;
import icesi.edu.co.DAOService.SalesTaxRateDAOService;
import icesi.edu.co.DAOService.StateProvinceDAOService;
import icesi.edu.co.main.BasicInfo;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Person;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.sales.Salestaxrate;
import icesi.edu.co.services.CountryRegionServiceImpl;
import icesi.edu.co.services.PersonServiceImpl;
import icesi.edu.co.services.SalesTaxRateServiceimpl;
import icesi.edu.co.services.StateProvinceServiceImpl;


@RestController
public class AdminRestController {
	
	private CountryRegionServiceImpl countryregionService;
	private SalesTaxRateServiceimpl salestaxrateService;
	private StateProvinceServiceImpl stateprovinceService;
	private PersonServiceImpl personService;
	@Autowired
	public AdminRestController(CountryRegionServiceImpl countryregionService, SalesTaxRateServiceimpl salestaxrateService,
			StateProvinceServiceImpl stateprovinceService,PersonServiceImpl personService) {
		this.countryregionService = countryregionService;
		this.salestaxrateService = salestaxrateService;
		this.stateprovinceService = stateprovinceService;
		this.personService = personService;
	}
	

	@RequestMapping(value = "/countries/{id}", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> getCountryregion(@PathVariable(value = "id") Integer id){
		Countryregion cr = countryregionService.getCountryRegion(id);
		return new ResponseEntity<Countryregion>(cr, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> getAllCountryregion() {
		List<Countryregion> countries = (List<Countryregion>) countryregionService.findAll();
		return new ResponseEntity(countries, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/countries", method = RequestMethod.POST)
	public ResponseEntity<Countryregion> createCountryregion(@Validated(BasicInfo.class) @RequestBody Countryregion cr){
		countryregionService.save(cr);
		return new ResponseEntity<Countryregion>(cr, HttpStatus.OK);
	}
	
	@PutMapping("/countries/{id}")
	public ResponseEntity<Countryregion> updateCountryregion(@Validated(BasicInfo.class) @RequestBody Countryregion cr){
		
		countryregionService.update(cr);
		
		return ResponseEntity.ok(cr);
	}
 
	
	//--------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
	public ResponseEntity<Salestaxrate> getSalestaxrate(@PathVariable(value = "id") Integer id){
		Salestaxrate sr = salestaxrateService.getSalestaxrate(id);
		return new ResponseEntity<Salestaxrate>(sr, HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public ResponseEntity<Salestaxrate> getAllSalestaxrate() {
		List<Salestaxrate> salestaxrates = (List<Salestaxrate>) salestaxrateService.findAll();
		return new ResponseEntity(salestaxrates, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sales", method = RequestMethod.POST)
	public ResponseEntity<Salestaxrate> createSalestaxrate(@Validated(BasicInfo.class) @RequestBody Salestaxrate sr){
		
		salestaxrateService.save(sr, 1);
		
		return new ResponseEntity<Salestaxrate>(sr, HttpStatus.OK);
	}
	
	@PutMapping("/sales/{id}")
	public ResponseEntity<Salestaxrate> updateSalestaxrate(@Validated(BasicInfo.class) @RequestBody Salestaxrate sr){
		
		salestaxrateService.update(sr, sr.getStateprovinceid());
		
		return ResponseEntity.ok(sr);
	}
	

	//----------------------------------------------------------------------------------------------
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public ResponseEntity<Stateprovince> getAllStatesprovinces() {
		List<Stateprovince> stateprovinces = (List<Stateprovince>) stateprovinceService.findAll();
		return new ResponseEntity(stateprovinces, HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<Person> getAllPersons() {
		List<Person> persons = (List<Person>) personService.findAll();
		return new ResponseEntity(persons, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public ResponseEntity<Person> createPerson(@Validated(BasicInfo.class) @RequestBody Person person){
		
		personService.save(person);
		
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	
}

