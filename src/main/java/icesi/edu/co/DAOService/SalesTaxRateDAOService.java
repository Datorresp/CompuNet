package icesi.edu.co.DAOService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import icesi.edu.co.DAO.CountryRegionDao;
import icesi.edu.co.DAO.SalestaxRateDao;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.sales.Salestaxrate;

public class SalesTaxRateDAOService {

	private SalestaxRateDao salesRepo;
	
	@Autowired
	public SalesTaxRateDAOService(SalestaxRateDao salesRepo) {
		this.salesRepo = salesRepo;
	}
	
	public void save(Salestaxrate t) {
		salesRepo.save(t);
	}
	
	public void update(Integer id) {
		
		Salestaxrate b = salesRepo.getByInt(id).get();	
		salesRepo.update(b);
		
	}
	
	public void delete(Salestaxrate t) {
		salesRepo.delete(t);
	}
	
	public Iterable<Salestaxrate> findAll(){
		return salesRepo.getAll();
	}
	
	public Optional<Salestaxrate> findByID(Integer id){
		return salesRepo.getByInt(id);
	}
}
