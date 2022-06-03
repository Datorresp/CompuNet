package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.person.Address;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.repository.AddressRepo;
import icesi.edu.co.repository.StateProvinceRepo;

@Service	
public class AddressServiceImpl implements AddressService{

	private AddressRepo addressRepo;
	private StateProvinceRepo stateprovinceRepo;

	
	@Autowired
	public AddressServiceImpl(AddressRepo addressRepo, StateProvinceRepo stateprovinceRepo) {
		this.addressRepo = addressRepo;
		this.stateprovinceRepo = stateprovinceRepo;
	}
	
	@Override
	public Address save(Address a, int i) {
		Address sAddress = null;
		
		boolean addressline1V = (a.getAddressline1() != null) && (!a.getAddressline1().isBlank());
		boolean cityV = (a.getCity() != null) && (a.getCity().length() >= 3);
		boolean postalcodeV = (a.getPostalcode() != null) && (String.valueOf(a.getPostalcode()).length() == 6);
		
		if(a.getPostalcode() != null && !a.getPostalcode().isBlank()){
			int number = Integer.parseInt(a.getPostalcode());	
		}
		
		if(addressline1V && cityV && postalcodeV) {
			
			Optional<Stateprovince> optional = this.stateprovinceRepo.findById((long) i);
			
			if(optional.isPresent()) {
				
				a.setStateprovince(optional.get());
				
				sAddress = this.addressRepo.save(a);
			}
		}
		
		
		
		
		return sAddress;
	}

	@Override
	public void update(Address a, long id) {
		
		Address ad = addressRepo.findById(id).get();
		ad.setAddressid(a.getAddressid());
		ad.setAddressline1(a.getAddressline1());
		ad.setAddressline2(a.getAddressline2());
		ad.setBusinessentityaddresses(a.getBusinessentityaddresses());
		ad.setCity(a.getCity());
		ad.setModifieddate(a.getModifieddate());
		ad.setPostalcode(a.getPostalcode());
		ad.setRowguid(a.getRowguid());
		ad.setSpatiallocation(a.getSpatiallocation());
		ad.setStateprovince(a.getStateprovince());
		addressRepo.save(ad);
		
	}

	@Override
	public Optional<Address> findByID(long id) {
		return addressRepo.findById(id);
	}

	@Override
	public void deleteByID(long id) {
		addressRepo.deleteById(id);
	}
	
	public Iterable<Address> findAll() {
		return addressRepo.findAll();
	}
	
	public Optional<Address> getAddress(Long id) {
		return addressRepo.findById(id);
	}

}
