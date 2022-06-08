package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import icesi.edu.co.person.Person;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer>{

}
