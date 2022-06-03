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

import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.sales.Salesterritory;


@Repository
@Scope("singleton")
public class StateProvinceDao implements Dao<Stateprovince> {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public StateProvinceDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Stateprovince entity) {
		entityManager.persist(entity);
	}

	@Override
	@Transactional
	public void update(Stateprovince entity) {
		entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void delete(Stateprovince entity) {
		entityManager.remove(entity);	
	}

	@Override
	@Transactional
	public Stateprovince getByInt(Integer codigo) {
		return entityManager.find(Stateprovince.class, codigo);
	}

	@Override
	@Transactional
	public List<Stateprovince> getAll() {
		String jpql = "Select a from Stateprovince a";
		return 	entityManager.createQuery(jpql,Stateprovince.class).getResultList();	
	}


	@Transactional
	public List<Stateprovince> getStateprovinceById(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.countryregion.countryregionid = '"+id+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}


	@Transactional
	public List<Stateprovince> getStateprovinceByTerritoryId(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.territoryid = '"+id+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	
	@Transactional
	public List<Stateprovince> getStateprovinceByName(String name) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name = '"+name+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	@Transactional
	public List<Object[]> getStateprovincesWithAddressAndSales(Salesterritory salesterritory) {
		String jpql = "SELECT stateprovince, COUNT(address.addressid) "
				+ "FROM Stateprovince stateprovince, Address address "
				+ "WHERE stateprovince.stateprovinceid = address.stateprovince.stateprovinceid"
				+ " AND stateprovince.territoryid = " + salesterritory.getTerritoryid()   
				+ " AND EXISTS(SELECT salestaxrate.stateprovince FROM Salestaxrate salestaxrate WHERE salestaxrate.stateprovince = stateprovince)"
				+ " GROUP BY stateprovince.stateprovinceid "
				+ "ORDER BY stateprovince.name";
		
		return entityManager.createQuery(jpql,Object[].class).getResultList();
	}

}
