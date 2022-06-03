package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.hr.Employee;

@Service
public interface EmployeeService {

	void save(Employee e);
	
	void update(Employee e, long id);
	
	Optional<Employee> findByID(long id);
	
	void deleteByID(long id);
}
