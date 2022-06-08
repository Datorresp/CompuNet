package icesi.edu.co.test.DAO;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import icesi.edu.co.DAO.AddressDao;
import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.main.TallerCompunetApplication;
import icesi.edu.co.person.Address;
import icesi.edu.co.person.Stateprovince;



@ContextConfiguration(classes = TallerCompunetApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestAddressDAO {
	
	@Autowired
	private AddressDao addressService;
	
	@Autowired
	private StateProvinceDao stateProvinceService;
	
	
	private Address address;
	
	@Autowired
	public TestAddressDAO(AddressDao addressService, StateProvinceDao stateProvinceService) {
		
		super();
		this.addressService = addressService;
		this.stateProvinceService = stateProvinceService;
	}
	
	void init() {
		address = new Address();
		address.setAddressline1("cll 14");
		address.setAddressline2("#83-50");
		address.setCity("Cali");
		address.setPostalcode("000000");
	}


	@Nested
	@Tag("Test")
	class DaoTestAddress{
		@Test
		@Order(1)
		void saveTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
				
			addressService.save(address);
			assertTrue(addressService.getByInt(address.getAddressid()).equals(address));
		}
		
		@Test
		@Order(2)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void updateTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
				
			addressService.save(address);
				
			address.setAddressline1("cra 83c");
			address.setCity("Cali");
			address.setPostalcode("111111");
				
			addressService.update(address);
				
			Address changed = addressService.getByInt(address.getAddressid());
				
			assertAll(                
				() -> assertEquals(changed.getAddressline1(),"cra 83c"),
				() -> assertEquals(changed.getAddressline2(),"#83-50"),
				() -> assertEquals(changed.getCity(),"Cali"),                 
				() -> assertEquals(changed.getPostalcode(),"111111")
			);
		}
		
		@Test
		@Order(3)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void deleteTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
			addressService.save(address);
			addressService.delete(address);
			
			Address delete = addressService.getByInt(address.getAddressid());
			
			assertNull(delete);
		}
		
		
		@Test
		@Order(4)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAllTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
			addressService.save(address);
			
			assertEquals(addressService.getAll().size(), 2);
			
		}
		
		
		@Test
		@Order(5)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findStateProvinceTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
			assertNotNull(stateProvinceService);
			
			Stateprovince state = new Stateprovince();
			stateProvinceService.save(state);
			
			address.setStateprovince(state);
			
			addressService.save(address);
			
			List<Address> list = addressService.getAddressByStateprovinceId(state.getStateprovinceid());
			
			assertEquals(list.size(), 1);
		}
		
		@Test
		@Order(6)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findCityTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
			addressService.save(address);
			
			Address aux = new Address();
			aux.setAddressline1("Calle 15 #121-25");
			aux.setCity("Cali");
			aux.setPostalcode("760008");
			
			addressService.save(aux);
			
			List<Address> list = addressService.getAddressByCity("Cali");
			
			assertEquals(list.size(), 3);
	
		}
		
		@Test
		@Order(7)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAddressTestDaoAddress() {
			
			init();
			assertNotNull(addressService);
			
			addressService.save(address);
			
			assertEquals(addressService.getByInt(address.getAddressid()), address);
		}

	}

}