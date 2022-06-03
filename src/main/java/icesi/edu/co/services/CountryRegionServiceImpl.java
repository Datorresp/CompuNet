package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Countryregion;
import icesi.edu.co.repository.CountryRegionRepo;


@Service
public class CountryRegionServiceImpl implements CountryregionService{

	private CountryRegionRepo countryRegionRepo;
	
	
	public CountryRegionServiceImpl(CountryRegionRepo countryRegionRepo) {
		super();
		this.countryRegionRepo = countryRegionRepo;
	}

	@Override
	public void save(Countryregion cr) {
	
		countryRegionRepo.save(cr);
		
	}

	@Override
	public void update(Countryregion cr, long id) {
		
		countryRegionRepo.deleteById(id);
		countryRegionRepo.save(cr);
	}

	@Override
	public Optional<Countryregion> findByID(long id) {

		return countryRegionRepo.findById(id);
	}
	
	public Iterable<Countryregion> findAll() {
		return countryRegionRepo.findAll();
	}

	@Override
	public void deleteByID(long id) {
		
		countryRegionRepo.deleteById(id);
	}

}
