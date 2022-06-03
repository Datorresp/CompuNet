package icesi.edu.co.DAOService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.person.Stateprovince;

public class StateProvinceDAOService {

	private StateProvinceDao stateRepo;
	
	@Autowired
	public StateProvinceDAOService(StateProvinceDao stateRepo) {
		this.stateRepo = stateRepo;
	}
	
	public void save(Stateprovince t) {
		stateRepo.save(t);
	}
	
	public void update(Integer id) {
		
		Stateprovince b = stateRepo.getByInt(id).get();	
		stateRepo.update(b);
		
	}
	
	public void delete(Stateprovince t) {
		stateRepo.delete(t);
	}
	
	public Iterable<Stateprovince> findAll(){
		return stateRepo.getAll();
	}
	
	public Optional<Stateprovince> findByID(Integer id){
		return stateRepo.getByInt(id);
	}
}
