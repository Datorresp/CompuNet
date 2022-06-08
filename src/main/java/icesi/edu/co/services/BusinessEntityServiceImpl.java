package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Businessentity;
import icesi.edu.co.repository.BusinessEntityRepo;


@Service
public class BusinessEntityServiceImpl implements BusinessEntityService{

	private BusinessEntityRepo beRepo;
	@Override
	public void save(Businessentity be) {
		
		beRepo.save(be);
	}

	@Override
	public void update(Businessentity be, Integer id) {
		
//		Businessentity be1 = beRepo.findById(id).get();
//		be1.setBusinessentityaddresses(be.getBusinessentityaddresses());
//		be1.setBusinessentitycontacts(be.getBusinessentitycontacts());
//		be1.setBusinessentityid(be.getBusinessentityid());
//		be1.setModifieddate(be.getModifieddate());
//		be1.setPerson(be.getPerson());
//		be1.setRowguid(be.getRowguid());
//		beRepo.save(be1);
	}

	@Override
	public Optional<Businessentity> findByID(Integer id) {
	
		return beRepo.findById(id);
	}

	@Override
	public void deleteByID(Integer id) {
		beRepo.deleteById(id);
		
	}

}
