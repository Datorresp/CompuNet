package icesi.edu.co.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import icesi.edu.co.person.Address;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.services.AddressServiceImpl;
import icesi.edu.co.services.CountryRegionServiceImpl;
import icesi.edu.co.services.StateProvinceServiceImpl;


@Controller
public class OperatorController {

	private StateProvinceServiceImpl stateprovinceService;
	private CountryRegionServiceImpl countryregionService;
	private AddressServiceImpl addressService;
	
	@Autowired
	public OperatorController(StateProvinceServiceImpl stateprovinceService, AddressServiceImpl addressService,CountryRegionServiceImpl countryregionService) {
		this.stateprovinceService = stateprovinceService;
		this.addressService = addressService;
		this.countryregionService = countryregionService;
	}
	
	@GetMapping("/user/province/")
    public String indexProvinces(Model model) {
		model.addAttribute("stateprovince", stateprovinceService.findAll());
        return "user/indexProvince";
    }
	
	@GetMapping("/user/province/add")
	public String addProvince(Model model) {
		model.addAttribute("stateprovince",new Stateprovince());
		model.addAttribute("countries", countryregionService.findAll());
		return "user/addProvince";
	}
	
	@PostMapping("/user/province/add")
	public String saveProvince( @ModelAttribute Stateprovince stateprovince,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			model.addAttribute("stateprovince", stateprovinceService.findAll());
			return "redirect:/user/province/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countries", countryregionService.findAll());
	        return "user/addProvince";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			stateprovinceService.save(stateprovince, stateprovince.getCountryregion().getCountryregionid());
		}
		return "redirect:/user/province/";
	}
	
	
	@GetMapping("/user/province/edit/{id}")
	public String showUpdateProvince(@PathVariable("id") Integer id,Model model) {
		Optional<Stateprovince> stateprovince = Optional.of(stateprovinceService.getStateProvince(id));
		if (stateprovince == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("stateprovince", stateprovince);
		model.addAttribute("countries", countryregionService.findAll());
		return "user/updateProvince";
	}
	
	@PostMapping("/user/province/edit/{id}")
	public String updateProvince(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @ModelAttribute Stateprovince stateprovince, BindingResult bindingResult, Model model) {

		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			System.out.println("Entre aca hola 1");
			return "redirect:/user/province/";
		}
		
		if(bindingResult.hasErrors()) {
			stateprovince.setStateprovinceid(id);
			model.addAttribute("stateprovince", stateprovince);
			model.addAttribute("countries", countryregionService.findAll());
			return "user/updateProvince";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			stateprovince.setStateprovinceid(id);
			stateprovinceService.update(stateprovince, id);
			model.addAttribute("provinces", stateprovinceService.findAll());
		}
		return "redirect:/user/province/";
	}
	

	@GetMapping("/user/address/")
	public String indexAddress(Model model) {
		model.addAttribute("addresses", addressService.findAll());
		return "user/indexAddress";
	}
	
	@GetMapping("/user/address/add")
	public String addAddress(Model model) {
		model.addAttribute("address",new Address());
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "user/addAddress";
	}
	
	@PostMapping("/user/address/add")
	public String saveCountry(@ModelAttribute Address address,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/user/address/";
		}
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("addresses", addressService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
	        return "user/addAddress";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			
			addressService.save(address, (int)address.getStateprovince().getStateprovinceid());
		}
		return "redirect:/user/address/";
	}
	
	@GetMapping("/user/address/edit/{id}")
	public String showUpdateAddress(@PathVariable("id") Integer id,Model model) {
		Optional<Address> address = Optional.of(addressService.getAddress(id));
		if (address == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
		
		model.addAttribute("address", address);
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "user/updateAddress";
	}
	
	@PostMapping("/user/address/edit/{id}")
	public String updateAddress(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @ModelAttribute Address address, BindingResult bindingResult, Model model) {
		
		if(action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/user/address/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("address", address);
			address.setAddressid(id);
			model.addAttribute("addresses", addressService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
			return "user/updateAddress";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			address.setAddressid(id);
			addressService.update(address,address.getStateprovince().getStateprovinceid());
			model.addAttribute("provinces", stateprovinceService.findAll());
		}
		return "redirect:/user/address/";
	}
	
}
