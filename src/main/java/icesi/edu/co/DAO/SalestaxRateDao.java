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
import icesi.edu.co.sales.Salestaxrate;


@Repository
@Scope("singleton")
public class SalestaxRateDao implements Dao<Salestaxrate>{

	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public SalestaxRateDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Salestaxrate entity) {
		entityManager.persist(entity);		
	}

	@Override
	@Transactional
	public void update(Salestaxrate entity) {
		entityManager.merge(entity);		
	}

	@Override
	@Transactional
	public void delete(Salestaxrate entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	@Transactional
	public Salestaxrate getByInt(Integer codigo) {
		return entityManager.find(Salestaxrate.class, codigo);
	}

	@Override
	@Transactional
	public List<Salestaxrate> getAll() {
		String jpql = "Select a from Salestaxrate a";
		return 	entityManager.createQuery(jpql,Salestaxrate.class).getResultList();	
	}


	@Transactional
	public List<Salestaxrate> getSalestaxrateByStateprovince(Integer id) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.stateprovince.stateprovinceid = '"+id+"'";
		return entityManager.createQuery(jpql,Salestaxrate.class).getResultList();
	}


	@Transactional
	public List<Salestaxrate> getSalestaxrateByName(String name) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.name = '"+ name + "'";
		return entityManager.createQuery(jpql,Salestaxrate.class).getResultList();
	}

}
