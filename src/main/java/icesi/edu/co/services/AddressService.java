package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Address;

@Service
public interface AddressService {

	Address save(Address a, Integer i);
	
	void update(Address a, Integer id);
	
	Optional<Address> findById(Integer id);
	
	void deleteByID(Integer id);


}
