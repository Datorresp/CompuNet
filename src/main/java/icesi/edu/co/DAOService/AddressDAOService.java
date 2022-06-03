package icesi.edu.co.DAOService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import icesi.edu.co.DAO.AddressDao;
import icesi.edu.co.person.Address;

@Service
public class AddressDAOService {

	private AddressDao addressRepo;
	
	@Autowired
	public AddressDAOService(AddressDao addressRepo) {
		this.addressRepo = addressRepo;
	}
	
	public void save(Address address) {
		addressRepo.save(address);
	}
	
	public void update(Address address, Integer id) {
		
		Address b = addressRepo.getByInt(id).get();	
		addressRepo.update(b);
		
	}
	
	public void delete(Address bill) {
		addressRepo.delete(bill);
	}
	
	public Iterable<Address> findAll(){
		return addressRepo.getAll();
	}
	
	public Optional<Address> findByID(Integer id){
		return addressRepo.getByInt(id);
	}
	
	
	
}
