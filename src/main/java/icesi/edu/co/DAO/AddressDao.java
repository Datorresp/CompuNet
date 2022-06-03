package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import icesi.edu.co.person.Address;

public class AddressDao implements Dao<Address> {

	@PersistenceUnit
	private EntityManager entityMF;
	
	@Override
	@Transactional
	public void save(Address t) {

		entityMF.getTransaction().begin();
		entityMF.persist(t);
		entityMF.getTransaction().commit();
		entityMF.close();
		
	}

	@Override
	@Transactional
	public void update(Address t) {

		entityMF.getTransaction().begin();
		entityMF.merge(t);
		entityMF.getTransaction().commit();
		entityMF.close();
		
	}

	@Transactional
	public void delete(Address t) {
		entityMF.getTransaction().begin();
		entityMF.remove(t);
		entityMF.getTransaction().commit();
		entityMF.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {

		Query query = entityMF.createQuery("SELECT e FROM Product e");
		return query.getResultList();
	}

	@Override
	public Optional<Address> getByInt(Integer id) {

		return Optional.ofNullable(entityMF.find(Address.class, id));
	}

	@Override
	public Optional<Address> getByLong(long id) {

		return Optional.ofNullable(entityMF.find(Address.class, id));
	}
	
	
	@Transactional
	public List<Address> findAll() {
		String jpql = "Select a from Address a";
		return 	((EntityManager) entityMF).createQuery(jpql,Address.class).getResultList();
	}

	
	@Transactional
	public List<Address> getAddressByStateprovinceId(Integer id) {
		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid = '" +id +"'";
        return ((EntityManager) entityMF).createQuery(jpql,Address.class).getResultList();
	}

	
	@Transactional
	public  List<Address> getAddressByCity(String city) {
	       String jpql = "SELECT a FROM Address a WHERE a.city = '" + city + "'";
	       return ((EntityManager) entityMF).createQuery(jpql,Address.class).getResultList();
	}

}
