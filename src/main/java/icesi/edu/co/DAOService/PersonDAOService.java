package icesi.edu.co.DAOService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.Dao;
import icesi.edu.co.person.Person;

@Service
public class PersonDAOService implements  Dao<Person>{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public PersonDAOService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Person save(Person entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public Person update(Person entity) {
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public void delete(Person entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Person> getAll() {
		String jpql = "Select a from Person a";
		return entityManager.createQuery(jpql, Person.class).getResultList();
	}

	@Override
	@Transactional
	public Person getByInt(Integer id) {
		return entityManager.find(Person.class, id);
	}
}
