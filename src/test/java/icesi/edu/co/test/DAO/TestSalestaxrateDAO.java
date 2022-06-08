package icesi.edu.co.test.DAO;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

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

import icesi.edu.co.DAO.SalestaxRateDao;
import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.main.TallerCompunetApplication;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.sales.*;

@ContextConfiguration(classes = TallerCompunetApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestSalestaxrateDAO {
	
	 @Autowired
	 private SalestaxRateDao salestaxrateDAO;
	 
	 @Autowired
	 private StateProvinceDao stateService;
	 
	 private Salestaxrate salestaxrate;
	 


	 @Autowired
	 public TestSalestaxrateDAO(SalestaxRateDao salestaxrateDAO, StateProvinceDao stateService) {
		super();
		
		this.salestaxrateDAO = salestaxrateDAO;
		this.stateService = stateService;
		
	}


	void init() {
		
			salestaxrate = new Salestaxrate();
			salestaxrate.setTaxrate(new BigDecimal("12345.123456789"));
			salestaxrate.setName("cinco");
			
	 }
	 
	 
	 @Nested
		@Tag("TestSalestaxrate")
		class DaoTestSalestaxrate{
		 
		 @Test
		 @Order(1)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void saveTestSalestaxrate1() {
			 
			 init();
			 assertNotNull(salestaxrateDAO);
			 
			 salestaxrateDAO.save(salestaxrate);
			 assertTrue(salestaxrateDAO.getByInt(salestaxrate.getSalestaxrateid()).equals(salestaxrate));
			 
		 }
		 
		 @Test
		 @Order(2)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void updateTestSalestaxrateDAO() {
			 init();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 
			 salestaxrate.setTaxrate(new BigDecimal("9087654321.0987654321"));
			 salestaxrate.setName("casita");
			 
			 salestaxrateDAO.update(salestaxrate);
			 
			 Salestaxrate salestaxrateChanged = salestaxrateDAO.getByInt(salestaxrate.getSalestaxrateid());
			 
				assertAll(                
						() -> assertEquals(salestaxrateChanged.getTaxrate(),new BigDecimal("9087654321.0987654321")),
						() -> assertEquals(salestaxrateChanged.getName(),"casita")
						);
		 }
		 
		 @Test
		 @Order(3)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void deleteTestSalestaxrateDAO() {
			 
			 init();
			 assertNotNull(salestaxrateDAO);
			 
			 salestaxrateDAO.save(salestaxrate);
			 salestaxrateDAO.delete(salestaxrate);	 
			 Salestaxrate delete = salestaxrateDAO.getByInt(salestaxrate.getSalestaxrateid());

			 assertNull(delete);
		
		 }
		 
		 @Test
		 @Order(4)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void findAllTestSalestaxrateDAO() {
			 
			 init();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 
			assertEquals(salestaxrateDAO.getAll().size(), 2);

		 }
		 
		 @Test
		 @Order(5)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void getSalestaxrateByStateprovinceTestDAO() {
			 
			 init();
			 assertNotNull(salestaxrateDAO);
			 assertNotNull(stateService);
			 
			 Stateprovince state = new Stateprovince();
			 stateService.save(state);
			 salestaxrate.setStateprovince(state);
				
			 salestaxrateDAO.save(salestaxrate);
			 
			 
			 List<Salestaxrate> lisSales =  salestaxrateDAO.getSalestaxrateByStateprovince(state.getStateprovinceid());
			 
			 assertEquals(lisSales.size(), 1);
			 
		 }
		 
		 @Test
		 @Order(6)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void getSalestaxrateByNameTestDAO() {
			 init();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 
			 assertEquals(salestaxrateDAO.getSalestaxrateByName(salestaxrate.getName()).size(), 1);
			 
		 }
		 
		 
	 }

}
