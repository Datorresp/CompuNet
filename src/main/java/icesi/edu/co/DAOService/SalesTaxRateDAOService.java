package icesi.edu.co.DAOService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.SalestaxRateDao;
import icesi.edu.co.sales.Salestaxrate;


@Service
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
		
		Salestaxrate b = salesRepo.getByInt(id);	
		salesRepo.update(b);
		
	}
	
	public void delete(Salestaxrate t) {
		salesRepo.delete(t);
	}
	
	public Iterable<Salestaxrate> findAll(){
		return salesRepo.getAll();
	}
	
	public Optional<Salestaxrate> findByID(Integer id){
		return Optional.of(salesRepo.getByInt(id));
	}
}
