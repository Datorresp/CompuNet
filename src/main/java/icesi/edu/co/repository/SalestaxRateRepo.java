package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import icesi.edu.co.sales.Salestaxrate;

@Repository

public interface SalestaxRateRepo extends CrudRepository<Salestaxrate, Integer>{

}
