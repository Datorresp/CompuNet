package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Countryregion;

@Service
public interface CountryregionService {

	
	Countryregion save(Countryregion cr);
	
	Countryregion update(Countryregion cr);
	
	Optional<Countryregion> findByID(Integer id);
	
	void deleteByID(Integer id);


}
