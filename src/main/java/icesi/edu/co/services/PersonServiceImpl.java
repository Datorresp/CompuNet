package icesi.edu.co.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAOService.PersonDAOService;
import icesi.edu.co.person.Person;

@Service
public class PersonServiceImpl {

	
	private PersonDAOService personDAO;

	@Autowired
	public PersonServiceImpl(PersonDAOService personDAO) {
		this.personDAO = personDAO;
	}
	
	@Transactional
	public Person save(Person person) {		
		Person aux = null;
		boolean one = (person.getFirstname() != null) && (person.getLastname() != null);

		if (one) {
			aux = this.personDAO.save(person);	
		}else {
			return aux = null;
		}
		
		return aux;	
	}
	
	@Transactional
	public Iterable<Person> findAll() {
		return personDAO.getAll();
	}
	
	@Transactional
	public void delete(Person person) {
		personDAO.delete(person);

	}
	
	@Transactional
	public Person findById(Integer id) {
		return personDAO.getByInt(id);
	}
	
	@Transactional
	public void update(Person person) {
		Person modPerson = personDAO.getByInt(person.getBusinessentityid());
		
		if(modPerson != null) {
			modPerson.setFirstname(person.getFirstname());
			modPerson.setLastname(person.getLastname());
			//modPerson.setTitle(person.getTitle());
			personDAO.save(modPerson);	
		}
	}
	
	@Transactional
	public Iterable<Person> findAllById(long id) {
		ArrayList<Person> persons = (ArrayList<Person>) personDAO.getAll();
		return persons;
	}
}
