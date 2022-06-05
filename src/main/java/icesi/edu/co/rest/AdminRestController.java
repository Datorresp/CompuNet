package icesi.edu.co.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import icesi.edu.co.person.Countryregion;


@RestController
public class AdminRestController {
	
	AddressDAOService addressService;
	CountryRegionDAOService countryService;
	SalesTaxRateDAOService salesService;
	StateProvinceDAOService stateService;

	
	@Autowired
	public AdminRestController(AddressDAOService addressService, CountryRegionDAOService countryService, SalesTaxRateDAOService salesService, StateProvinceDAOService stateService) {

		this.addressService = addressService;
		this.countryService = countryService;
		this.salesService = salesService;
		this.stateService = stateService;
	}
	
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> getProduct(@PathVariable(value = "id") int id) {
		//Optional<Countryregion> p = countryService.findByID(id);
		return new ResponseEntity<Countryregion>(HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> listProduct() {
		List<Countryregion> entities = ((List<Countryregion>) countryService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/countries", method = RequestMethod.POST)
	public ResponseEntity<Countryregion> createProduct( @RequestBody Countryregion pe) {
		countryService.save(pe);
		return new ResponseEntity<Countryregion>(pe, HttpStatus.CREATED);
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Countryregion> updatePerson(@RequestBody Countryregion pe) {

		return ResponseEntity.ok(pe);
	}
	
	
}
