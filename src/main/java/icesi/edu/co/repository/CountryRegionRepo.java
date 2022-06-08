package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import icesi.edu.co.person.Countryregion;

@Repository

public interface CountryRegionRepo extends CrudRepository<Countryregion, Integer> {

}
