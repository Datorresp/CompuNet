package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import icesi.edu.co.person.Countryregion;
import icesi.edu.co.sales.Salestaxrate;

public class SalestaxRateDao implements Dao<Salestaxrate>{

	
	@PersistenceUnit
	private EntityManager  em;
	
	@Override
	@Transactional
	public void save(Salestaxrate t) {

		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	@Transactional
	public void update(Salestaxrate t) {


		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Transactional
	public void delete(Salestaxrate t) {

		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salestaxrate> getAll() {

		Query query = em.createQuery("SELECT e FROM Product e");
		return query.getResultList();
	}

	@Override
	public Optional<Salestaxrate> getByInt(Integer id) {

		return Optional.ofNullable(em.find(Salestaxrate.class, id));
	}

	@Override
	public Optional<Salestaxrate> getByLong(long id) {

		return Optional.ofNullable(em.find(Salestaxrate.class, id));
	}
	
	@Transactional
	public List<Salestaxrate> findAll() {
		String jpql = "Select a from Salestaxrate a";
		return 	em.createQuery(jpql,Salestaxrate.class).getResultList();	
	}

	@Transactional
	public List<Salestaxrate> getSalestaxrateByStateprovince(Integer id) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.stateprovince.stateprovinceid = '"+id+"'";
		return em.createQuery(jpql,Salestaxrate.class).getResultList();
	}

	@Transactional
	public List<Salestaxrate> getSalestaxrateByName(String name) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.name = '"+ name + "'";
		return em.createQuery(jpql,Salestaxrate.class).getResultList();
	}

}
