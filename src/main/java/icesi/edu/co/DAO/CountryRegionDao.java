package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import icesi.edu.co.person.Countryregion;

public class CountryRegionDao implements Dao<Countryregion>{

	@PersistenceUnit
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(Countryregion t) {


		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	@Transactional
	public void update(Countryregion t) {

		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Transactional
	public void delete(Countryregion t) {

		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Countryregion> getAll() {

		Query query = em.createQuery("SELECT e FROM Product e");
		return query.getResultList();
	}

	@Override
	public Optional<Countryregion> getByInt(Integer id) {

		return Optional.ofNullable(em.find(Countryregion.class, id));
	}

	@Override
	public Optional<Countryregion> getByLong(long id) {

		return Optional.ofNullable(em.find(Countryregion.class, id));
	}
	
	@Transactional
	public List<Countryregion> findAll() {
		String jpql = "Select a from Countryregion a";
		return 	em.createQuery(jpql,Countryregion.class).getResultList();
	}
}
