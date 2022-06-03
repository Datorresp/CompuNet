package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.co.person.Countryregion;


@Repository
@Scope("singleton")
public class CountryRegionDao implements Dao<Countryregion>{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public  CountryRegionDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Countryregion entity) {
		entityManager.persist(entity);
		
	}

	@Override
	@Transactional
	public void update(Countryregion entity) {
		entityManager.merge(entity);
		
	}

	@Override
	@Transactional
	public void delete(Countryregion entity) {
		entityManager.remove(entity);
	}

	@Override
	@Transactional
	public Countryregion getByInt(Integer id) {
		return entityManager.find(Countryregion.class, id);
	}

	
	@Override
	@Transactional
	public List<Countryregion> getAll() {
		String jpql = "Select a from Countryregion a";
		return 	entityManager.createQuery(jpql,Countryregion.class).getResultList();
	}
}
