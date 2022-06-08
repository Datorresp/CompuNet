package icesi.edu.co.DAOService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import icesi.edu.co.DAO.Dao;
import icesi.edu.co.hr.Employee;


@Repository
@Scope("singleton")
public class EmployeeDAOService implements Dao<Employee>{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EmployeeDAOService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Employee save(Employee t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public Employee update(Employee t) {
		entityManager.merge(t);
		return t;
	}

	@Override
	public List<Employee> getAll() {
		String jpql = "Select a from Employee a";
		return entityManager.createQuery(jpql, Employee.class).getResultList();
	}

	@Override
	public Employee getByInt(Integer id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	public void delete(Employee entity) {
		entityManager.remove(entity);
		
	}

}
