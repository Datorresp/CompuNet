package icesi.edu.co.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.CountryRegionDao;
import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Stateprovince;

@Service
public class StateProvinceServiceImpl  implements StateProvinceService{

	private StateProvinceDao stateRepo;
	private CountryRegionDao countryRepo;
	
	@Autowired
	public StateProvinceServiceImpl(StateProvinceDao stateRepo, CountryRegionDao countryRepo) {
		this.stateRepo = stateRepo;
		this.countryRepo = countryRepo;
	}
	
	@Transactional
	@Override
	public Stateprovince save(Stateprovince entity, Integer countryregionid) {
		
		Stateprovince aux = null;
		boolean one = (entity.getStateprovincecode() != null) && (String.valueOf(entity.getStateprovincecode()).length() >= 5);
		boolean two = (entity.getIsonlystateprovinceflag() != null) && (entity.getIsonlystateprovinceflag().equals("Y") || entity.getIsonlystateprovinceflag().equals("N"));
		boolean three = (entity.getName() != null) && entity.getName().length() >= 5;
		
		if(entity.getStateprovincecode() != null && !entity.getStateprovincecode().isBlank()) {
			int number = Integer.parseInt(entity.getStateprovincecode());
		}
		
		if(one && two && three) {
			Optional<Countryregion> optional = Optional.ofNullable(this.countryRepo.getByInt(countryregionid));
			
			if(optional.isPresent()) {
				
				entity.setCountryregion(optional.get());
				aux = this.stateRepo.save(entity);
			}else {
				return aux = null;
			}
			
		}else {
			return aux = null;
		}
		
		return aux;
		
	}
	
	
	@Transactional
	@Override
	public Stateprovince update(Stateprovince entity, Integer countryregionid) {
		
		boolean one = (entity.getStateprovincecode() != null) && (String.valueOf(entity.getStateprovincecode()).length() >= 5);
		boolean two = (entity.getIsonlystateprovinceflag() != null) && (entity.getIsonlystateprovinceflag().equals("Y") || entity.getIsonlystateprovinceflag().equals("N"));
		boolean three = (entity.getName() != null) && entity.getName().length() >= 5;
		
		if(entity.getStateprovincecode() != null && !entity.getStateprovincecode().isBlank()) {
			int number = Integer.parseInt(entity.getStateprovincecode());
		}
		
		if(one && two && three) {
		if(entity.getStateprovinceid() != 0) {
			Optional<Stateprovince> optinalEntity = Optional.ofNullable(stateRepo.getByInt(entity.getStateprovinceid()));
			Optional<Countryregion> optional = Optional.ofNullable(this.countryRepo.getByInt(countryregionid));
			if(optinalEntity.isPresent() && optional.isPresent()) {
				entity.setCountryregion(optional.get());
				entity = this.stateRepo.update(entity);
			}else {
				return entity = null;
			}
		 }
	   }else {
			return entity = null;
		}
		
		return entity;
		
	}
	@Transactional
	@Override
	public Optional<Stateprovince> findById(Integer id) {
		return Optional.ofNullable(stateRepo.getByInt(id));
	}
	@Transactional
	public Iterable<Stateprovince> findAll() {
		return stateRepo.getAll();
	}
	@Transactional
	public Stateprovince getStateProvince(Integer id) {
		
		return stateRepo.getByInt(id);
	}

	@Override
	public void deleteByID(Integer id) {
		
		Stateprovince s = stateRepo.getByInt(id);
		stateRepo.delete(s);
		
	}
}
