package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Stateprovince;


@Service
public interface StateProvinceService {

	void save(Stateprovince sp);
	
	void update(Stateprovince sp, long id);
	
	Optional<Stateprovince> findByID(long id);
	
	void deleteByID(long id);
}
