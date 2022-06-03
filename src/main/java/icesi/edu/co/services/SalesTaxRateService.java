package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.sales.Salestaxrate;

@Service
public interface SalesTaxRateService {

	void save(Salestaxrate sxr);
	
	void update(Salestaxrate sxr, long id);
	
	Optional<Salestaxrate> findByID(long id);
	
	void deleteByID(long id);
}
