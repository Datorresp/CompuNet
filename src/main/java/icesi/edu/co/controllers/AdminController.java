package icesi.edu.co.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import icesi.edu.co.person.Countryregion;
import icesi.edu.co.prchasing.Productvendor;
import icesi.edu.co.prod.Billofmaterial;
import icesi.edu.co.prod.Product;
import icesi.edu.co.prod.Productreview;
import icesi.edu.co.sales.Salestaxrate;
import icesi.edu.co.services.CountryRegionServiceImpl;
import icesi.edu.co.services.CountryregionService;
import icesi.edu.co.services.SalesTaxRateService;
import icesi.edu.co.services.SalesTaxRateServiceimpl;
import icesi.edu.co.services.StateProvinceService;
import icesi.edu.co.services.StateProvinceServiceImpl;



@Controller
public class AdminController {

	private CountryRegionServiceImpl countryregionService;
	private SalesTaxRateServiceimpl salestaxrateService;
	private StateProvinceServiceImpl stateprovinceService;
	
	@Autowired
	public AdminController(CountryRegionServiceImpl countryregionService,SalesTaxRateServiceimpl salestaxrateService,StateProvinceServiceImpl stateprovinceService) {
		this.countryregionService = countryregionService;
		this.salestaxrateService = salestaxrateService;
		this.stateprovinceService = stateprovinceService;
	}
	
	@GetMapping("/admin/country/")
    public String indexCountry(Model model) {
		model.addAttribute("countries", countryregionService.findAll());
        return "admin/indexCountry";
    }
	
	@GetMapping("/admin/country/add")
	public String addCountry(Model model) {
		model.addAttribute("countryregion",new Countryregion());
		return "admin/addCountry";
	}
	
	@PostMapping("/admin/country/add")
	public String saveCountry(@ModelAttribute Countryregion countryregion,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equals("Cancelar")) {
			return "redirect:/admin/country/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("countries", countryregionService.findAll());
	        return "admin/addCountry";
		}
		if (!action.equalsIgnoreCase("cancel")) {
			System.out.println("Entre");
			countryregionService.save(countryregion);
			
		}
		
		
		return "redirect:/admin/country/";
	}
	
	
	@GetMapping("/admin/country/edit/{id}")
	public String showUpdateCountry(@PathVariable("id") Integer id,Model model) {
		Optional<Countryregion> countryregion = countryregionService.findByID(id);
		
		if (countryregion == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
			
		model.addAttribute("countryregion", countryregion);
		return "admin/updateCountry";
	}
	
	@PostMapping("/admin/country/edit/{id}")
	public String updateCountry( @ModelAttribute Countryregion countryregion, BindingResult bindingResult, Model model,@PathVariable("id") Long id, @RequestParam(value = "action", required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/country/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countryregion", countryregion);
			//countryregion.setCountryregionid(id);
			model.addAttribute("countries", countryregionService.findAll());
			return "admin/updateCountry";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			//countryregion.setCountryregionid(id);
			countryregionService.update(countryregion, id);
			model.addAttribute("countries", countryregionService.findAll());
		}
		return "redirect:/admin/country/";
	}
	
	//SalesTaxrate
	@GetMapping("/admin/sales/")
    public String indexSales(Model model) {
		model.addAttribute("salesses", salestaxrateService.findAll());
        return "admin/indexSales";
    }
	
	@GetMapping("/admin/sales/add")
	public String addSales(Model model) {
		model.addAttribute("salestaxrate",new Salestaxrate());
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "admin/addSales";
	}
	
	@PostMapping("/admin/sales/add")
	public String saveSales( @ModelAttribute Salestaxrate salestaxrate,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {		
			model.addAttribute("salesses", salestaxrateService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
	        return "admin/addSales";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			
			salestaxrateService.save(salestaxrate);
		}
		return "redirect:/admin/sales/";
	}
	
	
	@GetMapping("/admin/sales/edit/{id}")
	public String showUpdateSales(@PathVariable("id") Integer id,Model model) {
		Optional<Salestaxrate> salestaxrate = salestaxrateService.findByID(id);
		if (salestaxrate == null)
			throw new IllegalArgumentException("Invalid sales Id:" + id);
		
		model.addAttribute("salestaxrate", salestaxrate);
		model.addAttribute("provinces", stateprovinceService.findAll());
		return "admin/updateSales";
	}
	
	@PostMapping("/admin/sales/edit/{id}")
	public String updateSales(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,@ModelAttribute Salestaxrate salestaxrate, BindingResult bindingResult, Model model) {

		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel") ) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("salestaxrate", salestaxrate);
			salestaxrate.setSalestaxrateid(id);
			model.addAttribute("salesses", salestaxrateService.findAll());
			model.addAttribute("provinces", stateprovinceService.findAll());
			return "admin/updateSales";
		}
		if (!action.equalsIgnoreCase("Cancel") || !action.equalsIgnoreCase("Cancelar")) {
			salestaxrate.setSalestaxrateid(id);
			salestaxrateService.update(salestaxrate,salestaxrate.getStateprovinceid());
			model.addAttribute("salesses", salestaxrateService.findAll());
			
		}
		return "redirect:/admin/sales/";
	}
	
	
}
