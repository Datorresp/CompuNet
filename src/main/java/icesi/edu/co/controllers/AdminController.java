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

import icesi.edu.co.main.BasicInfo;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.sales.Salestaxrate;
import icesi.edu.co.services.CountryRegionServiceImpl;
import icesi.edu.co.services.SalesTaxRateService;
import icesi.edu.co.services.SalesTaxRateServiceimpl;
import icesi.edu.co.services.StateProvinceService;
import icesi.edu.co.services.StateProvinceServiceImpl;



@Controller
public class AdminController {

	private CountryRegionServiceImpl countryService;
	private SalesTaxRateServiceimpl salesService;
	private StateProvinceServiceImpl stateService;
	
	@Autowired
	public AdminController(CountryRegionServiceImpl countryService,SalesTaxRateServiceimpl salesService,StateProvinceServiceImpl stateService) {
		this.countryService = countryService;
		this.salesService = salesService;
		this.stateService = stateService;
	}
	
	@GetMapping("/admin/country/")
    public String indexCountry(Model model) {
		model.addAttribute("countries", countryService.findAll());
        return "admin/indexCountry";
    }
	
	@GetMapping("/admin/country/add")
	public String addCountry(Model model) {
		model.addAttribute("countryregion",new Countryregion());
		return "admin/addCountry";
	}
	
	@PostMapping("/admin/country/add")
	public String saveCountry(@Validated(BasicInfo.class) @ModelAttribute Countryregion countryregion,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equals("Cancelar")) {
			return "redirect:/admin/country/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("countries", countryService.findAll());
	        return "admin/addCountry";
		}
		if (!action.equalsIgnoreCase("cancel")) {
			System.out.println("Entre");
			countryService.save(countryregion);
			
		}
		
		
		return "redirect:/admin/country/";
	}
	
	
	@GetMapping("/admin/country/edit/{id}")
	public String showUpdateCountry(@PathVariable("id") Integer id,Model model) {
		Optional<Countryregion> countryregion = countryService.findByID(id);
		
		if (countryregion == null)
			throw new IllegalArgumentException("Invalid country Id:" + id);
			
		model.addAttribute("countryregion", countryregion);
		return "admin/updateCountry";
	}
	
	@PostMapping("/admin/country/edit/{id}")
	public String updateCountry(@Validated(BasicInfo.class) @ModelAttribute Countryregion countryregion, BindingResult bindingResult, Model model,@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/country/";
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("countryregion", countryregion);
			model.addAttribute("countries", countryService.findAll());
			return "admin/updateCountry";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			countryService.update(countryregion);
			model.addAttribute("countries", countryService.findAll());
		}
		return "redirect:/admin/country/";
	}
	
	//SalesTaxrate
	@GetMapping("/admin/sales/")
    public String indexSales(Model model) {
		model.addAttribute("salesses", salesService.findAll());
        return "admin/indexSales";
    }
	
	@GetMapping("/admin/sales/add")
	public String addSales(Model model) {
		model.addAttribute("salestaxrate",new Salestaxrate());
		model.addAttribute("provinces", stateService.findAll());
		return "admin/addSales";
	}
	
	@PostMapping("/admin/sales/add")
	public String saveSales(@Validated(BasicInfo.class) @ModelAttribute Salestaxrate salestaxrate,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {		
			model.addAttribute("salesses", salesService.findAll());
			model.addAttribute("provinces", stateService.findAll());
	        return "admin/addSales";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			
			salesService.save(salestaxrate, salestaxrate.getStateprovinceid());
		}
		return "redirect:/admin/sales/";
	}
	
	
	@GetMapping("/admin/sales/edit/{id}")
	public String showUpdateSales(@PathVariable("id") Integer id,Model model) {
		Salestaxrate salestaxrate = salesService.getSalestaxrate(id);
		if (salestaxrate == null)
			throw new IllegalArgumentException("Invalid sales Id:" + id);
		
		model.addAttribute("salestaxrate", salestaxrate);
		model.addAttribute("provinces", stateService.findAll());
		return "admin/updateSales";
	}
	
	@PostMapping("/admin/sales/edit/{id}")
	public String updateSales(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) @ModelAttribute Salestaxrate salestaxrate, BindingResult bindingResult, Model model) {

		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel") ) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("salestaxrate", salestaxrate);
			salestaxrate.setSalestaxrateid(id);
			model.addAttribute("salesses", salesService.findAll());
			model.addAttribute("provinces", stateService.findAll());
			return "admin/updateSales";
		}
		if (!action.equalsIgnoreCase("Cancel") || !action.equalsIgnoreCase("Cancelar")) {
			salestaxrate.setSalestaxrateid(id);
			salesService.update(salestaxrate,salestaxrate.getStateprovinceid());
			model.addAttribute("salesses", salesService.findAll());
			
		}
		return "redirect:/admin/sales/";
	}
	
}
