package icesi.edu.co.test.DAO;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import icesi.edu.co.DAO.CountryRegionDao;
import icesi.edu.co.main.TallerCompunetApplication;
import icesi.edu.co.person.*;

@ContextConfiguration(classes = TallerCompunetApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestCountryregionDAO {
	
	@Autowired
	private CountryRegionDao countryService;
	
	private Countryregion countryregion;
	
	@Autowired
	public TestCountryregionDAO(CountryRegionDao countryService) {
		
		super();
		this.countryService = countryService;
	}

	void init() { 
		
		countryregion = new Countryregion();
		countryregion.setCountryregioncode("12345");
		countryregion.setName("Polombia");
	}
	
	@Nested
	@Tag("TestCountryregion")
	class DaoTestCountryregion{
		
	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTestCountryregion() {
		
		init();
		assertNotNull(countryService);
		
		countryService.save(countryregion);
		assertTrue(countryService.getByInt(countryregion.getCountryregionid()).equals(countryregion));
	}	
		
	@Test
	@Order(2)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTestCountryregion() {
		
		init();
		assertNotNull(countryService);
		
		countryService.save(countryregion);
		
		countryregion.setName("Colombia");
    	countryregion.setCountryregioncode("1234");
  
    	countryService.update(countryregion);
    	
    	Countryregion countryregionEdited = countryService.getByInt(countryregion.getCountryregionid());
    	
    	assertAll(
    			() -> assertEquals(countryregionEdited.getName(),"Colombia"),
    			() -> assertEquals(countryregionEdited.getCountryregioncode(),"1234")
    	);
    	
	}
	@Test
	@Order(3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteTestCountryregion() {
		
		init();
		countryService.save(countryregion);
		
		countryService.delete(countryregion);
		
		assertNull(countryService.getByInt(countryregion.getCountryregionid()));
		
	}
	
	@Test
	@Order(4)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllTestCountryregion() {
		
		init();
		countryService.save(countryregion);
		
		assertEquals(countryService.getAll().size(), 2);
	}
	
	@Test
	@Order(5)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findCountryIdTestCountryregion() {
		
		init();
		countryService.save(countryregion);
		
		assertTrue(countryService.getByInt(countryregion.getCountryregionid()).equals(countryregion));
	}
	
  }
	
}
