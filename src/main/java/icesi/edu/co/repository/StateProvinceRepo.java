package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import icesi.edu.co.person.Stateprovince;

@Repository
public interface StateProvinceRepo extends CrudRepository<Stateprovince, Long>{
}
