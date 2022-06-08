package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import icesi.edu.co.person.Address;

@Repository
public interface AddressRepo extends CrudRepository<Address, Integer>{

}
