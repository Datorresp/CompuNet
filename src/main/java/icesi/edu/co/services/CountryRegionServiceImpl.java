package icesi.edu.co.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.CountryRegionDao;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.repository.CountryRegionRepo;


@Service
public class CountryRegionServiceImpl implements CountryregionService{
	
	private CountryRegionDao countryrepo;

	@Autowired
	public CountryRegionServiceImpl(CountryRegionDao countryregionDAO) {
		this.countryrepo = countryregionDAO;
	}
	
	@Transactional
	@Override
	public Countryregion save(Countryregion cr) {
		
		Countryregion aux = null;
		boolean one = (cr.getCountryregioncode() != null) && (cr.getCountryregioncode().length()>= 1 && cr.getCountryregioncode().length() <=4);
		boolean two = (cr.getName() != null) && (cr.getName().length() >= 5);
		
		
		if (one && two) {
			aux = this.countryrepo.save(cr);	
		}else {
			return aux = null;
		}
		
		return aux;	
		
	}
	
	@Transactional
	@Override
	public Countryregion update(Countryregion entity) {
		boolean one = (entity.getCountryregioncode() != null) && (entity.getCountryregioncode().length()>= 1 && entity.getCountryregioncode().length() <=4);
		boolean two = (entity.getName() != null) && (entity.getName().length() >= 5);
		
		
		if (one && two) {
		if(entity.getCountryregionid() != null) {
			Optional<Countryregion> optinalEntity = Optional.ofNullable(countryrepo.getByInt(entity.getCountryregionid()));
			if(optinalEntity.isPresent()) {
				entity = this.countryrepo.update(entity);
			}else {
				entity = null;
			}
		}
	}else {
		entity = null;
	}
		
		return entity;
	}
	
	@Transactional
	@Override
	public Optional<Countryregion> findByID(Integer id) {
		return Optional.ofNullable(countryrepo.getByInt(id));
	}
	@Transactional
	public Iterable<Countryregion> findAll() {
		return countryrepo.getAll();
	}
	@Transactional
	public Countryregion getCountryRegion(Integer id) {
		return countryrepo.getByInt(id);
	}


	@Override
	public void deleteByID(Integer id) {
		
		Countryregion cr = countryrepo.getByInt(id);
		countryrepo.delete(cr);
		
	}


}
