package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.repository.StateProvinceRepo;

@Service
public class StateProvinceServiceImpl  implements StateProvinceService{

	StateProvinceRepo sRepo;
	@Override
	public void save(Stateprovince sp) {
		
		Stateprovince aux = null;
		boolean one = (sp.getStateprovincecode() != null) && (String.valueOf(sp.getStateprovincecode()).length() >= 4);
		boolean two = (sp.getIsonlystateprovinceflag() != null) && (sp.getIsonlystateprovinceflag().equals("Y") || sp.getIsonlystateprovinceflag().equals("N"));
		boolean three = (sp.getName() != null) && sp.getName().length() >= 5;
		if(one && two && three) {
			System.out.println("saving");
			sRepo.save(sp);
		}else {
			
			System.out.println("NOT SAVING");
		}
		
	}

	@Override
	public void update(Stateprovince sp, long id) {
		
		boolean one = (sp.getStateprovincecode() != null) && (String.valueOf(sp.getStateprovincecode()).length() >= 5);
		boolean two = (sp.getIsonlystateprovinceflag() != null) && (sp.getIsonlystateprovinceflag().equals("Y") || sp.getIsonlystateprovinceflag().equals("N"));
		boolean three = (sp.getName() != null) && sp.getName().length() >= 5;
		if(one && two && three) {
			
			Stateprovince s = sRepo.findById(id).get();
			s.setAddresses(sp.getAddresses());
			s.setCountryregion(sp.getCountryregion());
			s.setIsonlystateprovinceflag(sp.getIsonlystateprovinceflag());
			s.setModifieddate(sp.getModifieddate());
			s.setName(sp.getName());
			s.setRowguid(sp.getRowguid());
			s.setStateprovincecode(s.getStateprovincecode());
			s.setStateprovinceid(sp.getStateprovinceid());
			s.setTerritoryid(sp.getTerritoryid());
			sRepo.save(s);
		}	
	}

	@Override
	public Optional<Stateprovince> findByID(long id) {
		
		return sRepo.findById(id);
	}

	@Override
	public void deleteByID(long id) {
		sRepo.deleteById(id);
		
	}

	public Iterable<Stateprovince> findAll() {
		return sRepo.findAll();
	}

	public Optional<Stateprovince> getStateProvince(long id) {
		
		return sRepo.findById(id);
	}
}
