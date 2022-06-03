package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;

import icesi.edu.co.person.NUser;

public interface UserRepository extends CrudRepository<NUser, String>{
	
	public NUser findByUsername(String name);
}
