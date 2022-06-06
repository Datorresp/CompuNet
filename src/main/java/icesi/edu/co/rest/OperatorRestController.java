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

import icesi.edu.co.main.BasicInfo;
import icesi.edu.co.person.Address;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.services.AddressServiceImpl;
import icesi.edu.co.services.CountryRegionServiceImpl;
import icesi.edu.co.services.StateProvinceServiceImpl;


@RestController
public class OperatorRestController {

	private CountryRegionServiceImpl countryregionService;
	private StateProvinceServiceImpl stateprovinceService;
	private AddressServiceImpl addressService;
	
	@Autowired
	public OperatorRestController(CountryRegionServiceImpl countryregionService,StateProvinceServiceImpl stateprovinceService,AddressServiceImpl addressService) {
		this.countryregionService = countryregionService;
		this.stateprovinceService = stateprovinceService;
		this.addressService = addressService;
	}

	
	//---------------------------------------------------------------------------------
	
	@RequestMapping(value="/provinces/{id}", method=RequestMethod.GET)
	public ResponseEntity<Stateprovince> getStateprovince(@PathVariable(value="id") Integer id){
		Stateprovince sp = stateprovinceService.findById(id).get();
		return new ResponseEntity<Stateprovince>(sp, HttpStatus.OK);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/provinces", method=RequestMethod.GET)
	public ResponseEntity<Stateprovince> listStateprovinces(){
		List<Stateprovince> entities = ((List<Stateprovince>) stateprovinceService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/provinces", method =RequestMethod.POST)
	public ResponseEntity<Stateprovince> createStateProvince(@Validated(BasicInfo.class) @RequestBody Stateprovince sp){
		stateprovinceService.save(sp, 1);
		return new ResponseEntity<Stateprovince>(sp, HttpStatus.CREATED);
	}
	
	@PutMapping("/provinces/{id}")
	public ResponseEntity<Stateprovince> updateStateProvince(@PathVariable(value="id")Integer id, @Validated(BasicInfo.class) @RequestBody Stateprovince sp){
		stateprovinceService.update(sp, id);
		return ResponseEntity.ok(sp);
	}

	//-----------------------------------------------------------------------------------
	
	@RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable(value = "id") Integer id) {
		Address a = addressService.findById(id).get();
		return new ResponseEntity<Address>(a, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public ResponseEntity<Address> listEntitiesAddresses() {
		List<Address> entities = ((List<Address>) addressService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addresses", method = RequestMethod.POST)
	public ResponseEntity<Address> createEntityAddress(@Validated(BasicInfo.class) @RequestBody Address a) {
		addressService.save(a, 1);
		return new ResponseEntity<Address>(a, HttpStatus.CREATED);
	}
	
	@PutMapping("/addresses/{id}")
	public ResponseEntity<Address> updateEntityAddress(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody Address a) {

		addressService.update(a, id);
		return ResponseEntity.ok(a);
	}
	

	//-----------------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> getAllCountryregion() {
		List<Countryregion> countries = (List<Countryregion>) countryregionService.findAll();
		return new ResponseEntity(countries, HttpStatus.OK);
	}
	
}
