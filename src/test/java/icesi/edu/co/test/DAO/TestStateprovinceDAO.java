package icesi.edu.co.test.DAO;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icesi.edu.co.DAO.AddressDao;
import icesi.edu.co.DAO.CountryRegionDao;
import icesi.edu.co.DAO.SalestaxRateDao;
import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.main.TallerCompunetApplication;
import icesi.edu.co.sales.*;
import icesi.edu.co.person.*;


@ContextConfiguration(classes = TallerCompunetApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestStateprovinceDAO {

	@Autowired
	private StateProvinceDao stateService;
	
	@Autowired
	private CountryRegionDao countryService;

	@Autowired
	private AddressDao addressService;
	
	@Autowired
	private SalestaxRateDao salestaxrateDAO;
	
	private Salestaxrate salestaxrate;
	
	private Stateprovince stateprovince;
	
	private Salesterritory salesterritory;
	
	@Autowired
	public TestStateprovinceDAO(StateProvinceDao stateService, CountryRegionDao countryService, SalestaxRateDao salestaxrateDAO) {
		
		super();
		this.stateService = stateService;
		this.countryService = countryService;
		this.salestaxrateDAO = salestaxrateDAO;
	}

	void init() { 
		
		salestaxrate = new Salestaxrate();
		salestaxrate.setTaxrate(new BigDecimal("12345.123456789"));
		salestaxrate.setName("Sale");
		
		stateprovince = new Stateprovince();
		stateprovince.setName("Province");
		stateprovince.setStateprovincecode("123456");
		
		salesterritory = new Salesterritory();
	}

	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTestStateprovince1(){
		
		init();
		assertNotNull(stateService);
		stateService.save(stateprovince);

		assertTrue(stateService.getByInt(stateprovince.getStateprovinceid()).equals(stateprovince));

	}

	@Test
	@Order(2)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTestStateprovince1() {
		
		init();
		assertNotNull(stateService);
		
		stateService.save(stateprovince);
		stateprovince.setName("province1");
		stateprovince.setStateprovincecode("123789");

		stateService.update(stateprovince);

		Stateprovince edited = stateService.getByInt(stateprovince.getStateprovinceid());


		assertAll(
				() -> assertEquals(edited.getName(),"province1"),
				() -> assertEquals(edited.getStateprovincecode(),"123789")
				);

	}

	@Test
	@Order(3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteTestStateprovince() {
		init();
		assertNotNull(stateService);
		stateService.save(stateprovince);

		stateService.delete(stateprovince);

		assertNull(stateService.getByInt(stateprovince.getStateprovinceid()));
	}
	
	@Test
	@Order(4)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findIdTestStateprovinceDAO1() {
		
		init();
		assertNotNull(stateService);
		
		stateService.save(stateprovince);
		
		assertEquals(stateService.getByInt(stateprovince.getStateprovinceid()),stateprovince);
	}

	@Test
	@Order(5)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllAddressesTestStateprovince() {
		
		init();
		assertNotNull(stateService);
		
		stateService.save(stateprovince);

		assertEquals(stateService.getAll().size(), 2);
	}

	@Test
	@Order(6)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getNameTestStateprovince() {
		
		init();
		stateService.save(stateprovince);

		stateprovince.setName("Valle");
		stateprovince.setStateprovincecode("111111");

		stateService.save(stateprovince);

		List<Stateprovince> lists = stateService.getStateprovinceByName("Valle");
		assertEquals(lists.size(), 2);
	}

	@Test
	@Order(7)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getCountryregionTestStateprovince() {
		
		
		init();
		Countryregion cr = new Countryregion();
		
		cr = new Countryregion();
		cr.setName("Colombia");
		cr.setCountryregioncode("123");

		countryService.save(cr);

		stateprovince.setCountryregion(cr);
		stateService.save(stateprovince);

		stateprovince = new Stateprovince();
		stateprovince.setName("Cundinamarca");
		stateprovince.setStateprovincecode("22222");
		
		stateprovince.setCountryregion(cr);
		stateService.save(stateprovince);
		
		List<Stateprovince> list = stateService.getStateprovinceById(cr.getCountryregionid());
		assertEquals(list.size(), 2);
	}
	
	 @Test
	 @Order(8)
	 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	 void getStateprovinceWithAddressAndSalesTaxrateDAO() {
		 init();
		 assertNotNull(addressService);
		 assertNotNull(stateService);
		 Stateprovince state = new Stateprovince();
		 
		 Address ad = new Address();
		 ad.setStateprovince(state);
		 
		 state.setTerritoryid(salesterritory.getTerritoryid());
		 
		 Stateprovince st = stateService.save(state);
		 ad.setStateprovince(st);
		 
		 addressService.save(ad);
		 
		 salestaxrate.setStateprovince(st);
		 
		 salestaxrateDAO.save(salestaxrate);
		 
		 
		 List<Object[]> listStates = stateService.getStateprovincesWithAddressAndSales(salesterritory);
		
		 assertEquals(2,listStates.size());
	 }
	
	
	
}
