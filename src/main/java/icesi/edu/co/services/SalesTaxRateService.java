package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.sales.Salestaxrate;

@Service
public interface SalesTaxRateService {

	Salestaxrate save(Salestaxrate sxr, Integer i);
	
	Salestaxrate update(Salestaxrate sxr, Integer id);
	
	Optional<Salestaxrate> findByID(Integer id);
	
	void deleteByID(Integer id);


}
