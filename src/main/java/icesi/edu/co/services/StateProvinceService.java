package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Stateprovince;


@Service
public interface StateProvinceService {

	Stateprovince save(Stateprovince sp, Integer i);
	
	Stateprovince update(Stateprovince sp, Integer id);
	
	Optional<Stateprovince> findById(Integer id);
	
	void deleteByID(Integer id);
}
