package icesi.edu.co.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import icesi.edu.co.hr.Employee;

@Repository

public interface EmployeeRepo extends CrudRepository<Employee, Long>{

}
