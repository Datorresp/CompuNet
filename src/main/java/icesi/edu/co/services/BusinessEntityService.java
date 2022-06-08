package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Businessentity;

@Service
public interface BusinessEntityService {

	void save(Businessentity be);
	
	void update(Businessentity be, Integer id);
	
	Optional<Businessentity> findByID(Integer id);
	
	void deleteByID(Integer id);
}
