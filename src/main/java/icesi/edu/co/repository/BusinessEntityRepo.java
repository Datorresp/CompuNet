package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import icesi.edu.co.person.Businessentity;

@Repository

public interface BusinessEntityRepo extends CrudRepository<Businessentity, Long>{

}
