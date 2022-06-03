package icesi.edu.co.rest;

import java.util.List;
import java.util.Optional;

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
		Optional<Countryregion> p = countryService.findByID(id);
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
	
	

//	//PRODUCT REVIEW
//	
////	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
////	public ResponseEntity<Productreview> getProductReview(@PathVariable(value = "id") int id) {
////		Productreview p = pr.findByID(id);
////		return new ResponseEntity<Productreview>(p, HttpStatus.OK);
////	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
//	public ResponseEntity<Productreview> listProductReview() {
//		List<Productreview> entities = ((List<Productreview>) pr.findAll());
//		return new ResponseEntity(entities, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
//	public ResponseEntity<Productreview> createProductReview(@Validated(Information.class) @RequestBody Productreview pe) {
//		pr.save(pe);
//		return new ResponseEntity<Productreview>(pe, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/reviews/{id}")
//	public ResponseEntity<Productreview> updatePersonReview(@Validated(Information.class) @RequestBody Productreview pe, int id) {
//
//		pr.update(pe, id);
//		return ResponseEntity.ok(pe);
//	}
//	
//	
//	//VENDORS
//	
//	@RequestMapping(value = "/vendors/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Productvendor> getVendor(@PathVariable(value = "id") int id) {
//		Productvendor p = vendorService.findByID(id);
//		return new ResponseEntity<Productvendor>(p, HttpStatus.OK);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
//	public ResponseEntity<Productvendor> listVendors() {
//		List<Productvendor> entities = ((List<Productvendor>) vendorService.findAll());
//		return new ResponseEntity(entities, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/vendors", method = RequestMethod.POST)
//	public ResponseEntity<Productvendor> createVendor(@Validated(Information.class) @RequestBody Productvendor pe) {
//		vendorService.save(pe);
//		return new ResponseEntity<Productvendor>(pe, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/vendors/{id}")
//	public ResponseEntity<Productvendor> updateVendor(@Validated(Information.class) @RequestBody Productvendor pe) {
//
//		//productService.(pe);
//		return ResponseEntity.ok(pe);
//	}
//	
//	// BILL OF MATERIAL
//
//	
////	@RequestMapping(value = "/bills/{id}", method = RequestMethod.GET)
////	public ResponseEntity<Billofmaterial> getBill(@PathVariable(value = "id") int id) {
////		Billofmaterial p = bill.ge(id);
////		return new ResponseEntity<Billofmaterial>(p, HttpStatus.OK);
////	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/bills", method = RequestMethod.GET)
//	public ResponseEntity<Billofmaterial> listBills() {
//		List<Billofmaterial> entities = ((List<Billofmaterial>) bill.findAll());
//		return new ResponseEntity(entities, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/bills", method = RequestMethod.POST)
//	public ResponseEntity<Billofmaterial> createBill(@Validated(Information.class) @RequestBody Billofmaterial pe) {
//		bill.save(pe);
//		return new ResponseEntity<Billofmaterial>(pe, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/bills/{id}")
//	public ResponseEntity<Billofmaterial> updateBill(@Validated(Information.class) @RequestBody Billofmaterial pe, int id) {
//
//		bill.update(pe, id);
//		return ResponseEntity.ok(pe);
//	}
}
