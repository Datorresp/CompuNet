package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.co.hr.Employee;
import icesi.edu.co.repository.EmployeeRepo;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepo eRepo;
	@Override
	public void save(Employee e) {
		
		eRepo.save(e);
	}

	@Override
	public void update(Employee e, long id) {
		
		Employee e1 = eRepo.findById(id).get();
		e1.setBirthdate(e.getBirthdate());
		e1.setBusinessentityid(e.getBusinessentityid());
		e1.setCurrentflag(e.getCurrentflag());
		e1.setEmployeedepartmenthistories(e.getEmployeedepartmenthistories());
		e1.setEmployeepayhistories(e.getEmployeepayhistories());
		e1.setGender(e.getGender());
		e1.setHiredate(e.getHiredate());
		e1.setJobcandidates(e.getJobcandidates());
		e1.setJobtitle(e.getJobtitle());
		e1.setLoginid(e.getLoginid());
		e1.setMaritalstatus(e.getMaritalstatus());
		e1.setModifieddate(e.getModifieddate());
		e1.setNationalidnumber(e.getNationalidnumber());
		e1.setOrganizationnode(e.getOrganizationnode());
		e1.setRowguid(e.getRowguid());
		e1.setSalariedflag(e.getSalariedflag());
		e1.setSickleavehours(e.getSickleavehours());
		e1.setVacationhours(e.getVacationhours());
		eRepo.save(e1);
	}

	@Override
	public Optional<Employee> findByID(long id) {
		
		return eRepo.findById(id);
	}

	@Override
	public void deleteByID(long id) {
		
		eRepo.deleteById(id);
	}



}
