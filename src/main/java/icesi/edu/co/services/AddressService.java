package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Address;

@Service
public interface AddressService {

	Address save(Address a, int i);
	
	void update(Address a, long id);
	
	Optional<Address> findByID(long id);
	
	void deleteByID(long id);
}
