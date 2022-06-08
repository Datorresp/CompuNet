package icesi.edu.co.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAOService.EmployeeDAOService;
import icesi.edu.co.hr.Employee;
import icesi.edu.co.person.Address;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDAOService eRepo;
	
	@Override
	@Transactional
	public void save(Employee e) {
		
		e.setPerson(null);
		eRepo.save(e);
	}
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAOService eRepo) {
		this.eRepo = eRepo;
	}

	@Override
	public void update(Employee e, Integer id) {
		
		boolean one = (e.getNationalidnumber() != null) && (e.getJobtitle()!=null);
		
		
		if (one) {
			if(e.getBusinessentityid() != null) {
				
				Optional<Employee> optinalEntity = Optional.ofNullable(eRepo.getByInt(e.getBusinessentityid()));
				if(optinalEntity.isPresent()) {
					
					e.setPerson(null);
					e = this.eRepo.update(e);
				}else {
					
					e = null;
				}
			}
		}else {
			
			e = null;
		}
	}

	@Override
	@Transactional
	public Optional<Employee> findByID(Integer id) {
		
		return Optional.of(eRepo.getByInt(id));
	}

	@Override
	@Transactional
	public void deleteByID(Integer id) {
		
		Employee e = eRepo.getByInt(id);
		eRepo.delete(e);
	}

	@Transactional
	public Iterable<Employee> findAll() {
		return eRepo.getAll();
	}


}
