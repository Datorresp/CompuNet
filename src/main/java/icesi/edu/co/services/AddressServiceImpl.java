package icesi.edu.co.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.AddressDao;
import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.person.Address;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.repository.AddressRepo;
import icesi.edu.co.repository.StateProvinceRepo;

@Service	
public class AddressServiceImpl implements AddressService{

	private AddressDao addressDao;
	private StateProvinceDao stateprovinceDao;
	
	//Constructor
	@Autowired
	public AddressServiceImpl(AddressDao addressDao, StateProvinceDao stateprovinceDao) {
		this.addressDao = addressDao;
		this.stateprovinceDao = stateprovinceDao;
	}

	
	@Transactional
	@Override
	public Address save(Address entity, Integer stateprovinceid) {
		Address sAddress = null;
		
		boolean addressline1V = (entity.getAddressline1() != null) && (!entity.getAddressline1().isBlank());
		boolean cityV = (entity.getCity() != null) && (entity.getCity().length() >= 3);
		boolean postalcodeV = (entity.getPostalcode() != null) && (String.valueOf(entity.getPostalcode()).length() == 6);
		
		if(entity.getPostalcode() != null && !entity.getPostalcode().isBlank()){
			int number = Integer.parseInt(entity.getPostalcode());	
		}
		
		if(addressline1V && cityV && postalcodeV) {
			
			Optional<Stateprovince> optional = Optional.ofNullable(this.stateprovinceDao.getByInt(stateprovinceid));
			
			if(optional.isPresent()) {
				
				entity.setStateprovince(optional.get());
				
				sAddress = this.addressDao.save(entity);
			}else {
				return sAddress = null;
			}
		}else {
			return sAddress = null;
		}	
		
		return sAddress;
	}
	
	@Transactional
	@Override
	public void update(Address entity, Integer stateprovinceid) {

		boolean addressline1V = (entity.getAddressline1() != null) && (!entity.getAddressline1().isBlank());
		boolean cityV = (entity.getCity() != null) && (entity.getCity().length() >= 3);
		boolean postalcodeV = (entity.getPostalcode() != null) && (String.valueOf(entity.getPostalcode()).length() == 6);
		
		if(entity.getPostalcode() != null && !entity.getPostalcode().isBlank()){
			int number = Integer.parseInt(entity.getPostalcode());	
		}
		
		if(addressline1V && cityV && postalcodeV) {
		if(entity.getAddressid() != null) {
			Optional<Address> optinalEntity = Optional.ofNullable(this.addressDao.getByInt(entity.getAddressid()));
			Optional<Stateprovince> optional = Optional.ofNullable(this.stateprovinceDao.getByInt(stateprovinceid));
			if(optinalEntity.isPresent() && optional.isPresent()) {
				entity.setStateprovince(optional.get());
				entity = addressDao.update(entity);
				
			}
	      }
		}
	} 
	
	
	@Transactional
	@Override
	public Optional<Address> findById(Integer id) {
		return Optional.ofNullable(this.addressDao.getByInt(id));
	}
	@Transactional
	public Iterable<Address> findAll() {
		return addressDao.getAll();
	}
	
	@Transactional
	public Address getAddress(Integer id) {
		return addressDao.getByInt(id);
	}

	@Transactional
	@Override
	public void deleteByID(Integer id) {
				
		Address a = addressDao.getByInt(id);
		addressDao.delete(a);
	}

}
