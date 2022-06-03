package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import icesi.edu.co.person.Stateprovince;

public class StateProvinceDao implements Dao<Stateprovince> {

	@PersistenceUnit
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Stateprovince t) {

		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	@Transactional
	public void update(Stateprovince t) {

		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Transactional
	public void delete(Stateprovince t) {

		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Stateprovince> getAll() {

		Query query = em.createQuery("SELECT e FROM Product e");
		return query.getResultList();
	}

	@Override
	public Optional<Stateprovince> getByInt(Integer id) {

		return Optional.ofNullable(em.find(Stateprovince.class, id));
	}

	@Override
	public Optional<Stateprovince> getByLong(long id) {
		return Optional.ofNullable(em.find(Stateprovince.class, id));
	}
	
	@Transactional
	public List<Stateprovince> findAll() {
		String jpql = "Select a from Stateprovince a";
		return 	em.createQuery(jpql,Stateprovince.class).getResultList();	
	}

	@Transactional
	public List<Stateprovince> getStateprovinceById(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.countryregion.countryregionid = '"+id+"'";
		return em.createQuery(jpql,Stateprovince.class).getResultList();
	}

	@Transactional
	public List<Stateprovince> getStateprovinceByTerritoryId(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.territoryid = '"+id+"'";
		return em.createQuery(jpql,Stateprovince.class).getResultList();
	}

	@Transactional
	public List<Stateprovince> getStateprovinceByName(String name) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name = '"+name+"'";
		return em.createQuery(jpql,Stateprovince.class).getResultList();
	}
}
