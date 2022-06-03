package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.SalestaxRateDao;
import icesi.edu.co.repository.SalestaxRateRepo;
import icesi.edu.co.sales.Salestaxrate;


@Service
public class SalesTaxRateServiceimpl implements SalesTaxRateService{

	SalestaxRateRepo sRepo;	
	
	public SalesTaxRateServiceimpl(SalestaxRateRepo sRepo) {
		this.sRepo = sRepo;
	}

	@Override
	public void save(Salestaxrate sxr) {
		sRepo.save(sxr); 
		
	}

	@Override
	public void update(Salestaxrate sxr, long id) {
		Salestaxrate s1 = sRepo.findById(id).get();
		s1.setModifieddate(sxr.getModifieddate());
		s1.setName(sxr.getName());
		s1.setRowguid(sxr.getRowguid());
		s1.setSalestaxrateid(sxr.getSalestaxrateid());
		s1.setStateprovinceid(sxr.getStateprovinceid());
		s1.setTaxrate(sxr.getTaxrate());
		s1.setTaxtype(sxr.getTaxtype());
		sRepo.save(s1);
	}

	@Override
	public Optional<Salestaxrate> findByID(long id) {
		
		return sRepo.findById(id);
	}

	@Override
	public void deleteByID(long id) {
		sRepo.deleteById(id);
		
	}

	public Iterable<Salestaxrate> findAll() {
		
		return sRepo.findAll();
	}

}
