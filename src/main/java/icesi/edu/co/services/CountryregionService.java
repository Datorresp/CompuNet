package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Countryregion;

@Service
public interface CountryregionService {

	
	void save(Countryregion cr);
	
	void update(Countryregion cr, long id);
	
	Optional<Countryregion> findByID(long id);
	
	void deleteByID(long id);

}
