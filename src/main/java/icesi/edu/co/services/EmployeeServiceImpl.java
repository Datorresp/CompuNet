package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.DAOService.EmployeeDAOService;
import icesi.edu.co.hr.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDAOService eRepo;
	
	@Override
	public void save(Employee e) {
		
		eRepo.save(e);
	}

	@Override
	public void update(Employee e, Integer id) {
		
		boolean one = (e.getNationalidnumber() != null) && (e.getJobtitle()!=null);
		boolean two = (e.getPerson() != null);
		
		
		if (one && two) {
			if(e.getBusinessentityid() != null) {
				Optional<Employee> optinalEntity = Optional.ofNullable(eRepo.getByInt(e.getBusinessentityid()));
				if(optinalEntity.isPresent()) {
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
	public Optional<Employee> findByID(Integer id) {
		
		return Optional.of(eRepo.getByInt(id));
	}

	@Override
	public void deleteByID(Integer id) {
		
		Employee e = eRepo.getByInt(id);
		eRepo.delete(e);
	}



}
