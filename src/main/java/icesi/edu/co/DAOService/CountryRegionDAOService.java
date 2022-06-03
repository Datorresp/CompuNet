package icesi.edu.co.DAOService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import icesi.edu.co.DAO.CountryRegionDao;
import icesi.edu.co.person.Countryregion;

public class CountryRegionDAOService {

	
	private CountryRegionDao countryRepo;
	
	@Autowired
	public CountryRegionDAOService(CountryRegionDao countryRepo) {
		this.countryRepo = countryRepo;
	}
	
	public void save(Countryregion t) {
		countryRepo.save(t);
	}
	
	public void update(Integer id) {
		
		Countryregion b = countryRepo.getByInt(id).get();	
		countryRepo.update(b);
		
	}
	
	public void delete(Countryregion t) {
		countryRepo.delete(t);
	}
	
	public Iterable<Countryregion> findAll(){
		return countryRepo.getAll();
	}
	
	public Optional<Countryregion> findByID(Integer id){
		return countryRepo.getByInt(id);
	}
}
