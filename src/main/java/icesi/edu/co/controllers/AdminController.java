package icesi.edu.co.controllers;

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

import icesi.edu.co.hr.Employee;
import icesi.edu.co.main.BasicInfo;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Person;
import icesi.edu.co.sales.Salestaxrate;



@Controller
public class AdminController {

	private DelegatedAdmin da;
	
	@Autowired
	public AdminController(DelegatedAdmin da) {
		this.da = da;
	}
	
	@GetMapping("/admin/country/")
    public String indexCountry(Model model) {
		model.addAttribute("countries", da.getAllCountries());
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
			
			model.addAttribute("countries", da.getAllCountries());
	        return "admin/addCountry";
		}
		if (!action.equalsIgnoreCase("cancel")) {
			System.out.println("Entre");
			da.CreateCountry(countryregion);
			
		}
		
		
		return "redirect:/admin/country/";
	}
	
	
	@GetMapping("/admin/country/edit/{id}")
	public String showUpdateCountry(@PathVariable("id") Integer id,Model model) {
		Countryregion countryregion = da.getCountry(id);
		
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
			countryregion.setCountryregionid(id);
			model.addAttribute("countries", da.getAllCountries());
			return "admin/updateCountry";
		}
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			countryregion.setCountryregionid(id);
			da.updateCountry(id, countryregion);
			model.addAttribute("countries", da.getAllCountries());
		}
		return "redirect:/admin/country/";
	}
	
	@GetMapping("/admin/addresses/")
	public String indexSpecialqueries(Model model) {
		model.addAttribute("addresses", da.specialQuery());
		return "admin/queries";
	}
	
	//SalesTaxrate
	@GetMapping("/admin/sales/")
    public String indexSales(Model model) {
		model.addAttribute("salesses", da.getAllSales());
        return "admin/indexSales";
    }
	
	@GetMapping("/admin/sales/add")
	public String addSales(Model model) {
		model.addAttribute("salestaxrate",new Salestaxrate());
		model.addAttribute("provinces", da.getAllStateprovince());
		return "admin/addSales";
	}
	
	@PostMapping("/admin/sales/add")
	public String saveSales(@Validated(BasicInfo.class) @ModelAttribute Salestaxrate salestaxrate,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel")) {
			return "redirect:/admin/sales/";
		}
		
		if(bindingResult.hasErrors()) {		
			model.addAttribute("sales", da.getAllSales());
			model.addAttribute("provinces", da.getAllStateprovince());
	        return "admin/addSales";
		}
		
		if (!action.equalsIgnoreCase("Cancelar") || !action.equalsIgnoreCase("Cancel")) {
			
			da.CreateSales(salestaxrate);
		}
		return "redirect:/admin/sales/";
	}
	
	
	@GetMapping("/admin/sales/edit/{id}")
	public String showUpdateSales(@PathVariable("id") Integer id,Model model) {
		Salestaxrate salestaxrate = da.getSales(id);
		if (salestaxrate == null)
			throw new IllegalArgumentException("Invalid sales Id:" + id);
		
		model.addAttribute("salestaxrate", salestaxrate);
		model.addAttribute("provinces", da.getAllStateprovince());
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
			model.addAttribute("salesses", da.getAllSales());
			model.addAttribute("provinces", da.getAllStateprovince());
			return "admin/updateSales";
		}
		if (!action.equalsIgnoreCase("Cancel") || !action.equalsIgnoreCase("Cancelar")) {
			salestaxrate.setSalestaxrateid(id);
			da.updateSales(salestaxrate.getStateprovince().getStateprovinceid(), salestaxrate);
			model.addAttribute("sales",da.getAllSales());
			
		}
		return "redirect:/admin/sales/";
	}
	
	
	
	@GetMapping("/admin/person/")
    public String indexPerson(Model model) {
		model.addAttribute("persons", da.getAllPersons());
        return "admin/indexPerson";
    }
	
	@GetMapping("/admin/person/add")
	public String addPerson(Model model) {
		model.addAttribute("person",new Person());
		return "admin/addPerson";
	}
	
	@PostMapping("/admin/person/add")
	public String savePerson(@Validated(BasicInfo.class) @ModelAttribute Person person,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equals("Cancelar")) {
			return "redirect:/admin/person/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("persons", da.getAllPersons());
	        return "admin/addPerson";
		}
		if (!action.equalsIgnoreCase("cancel")) {

			da.createPerson(person);
			
		}
		return "redirect:/admin/person/";
	}
	
	@GetMapping("/admin/person/edit/{id}")
	public String showUpdatePerson(@PathVariable("id") Integer id,Model model) {
		Employee employeee = da.getEmployee(id);
		if (employeee == null)
			throw new IllegalArgumentException("Invalid person Id:" + id);
		
		model.addAttribute("employee", employeee);
		model.addAttribute("persons", da.getAllPersons());
		return "admin/updateEmployee";
	}
	
	@PostMapping("/admin/person/edit/{id}")
	public String updatePerson(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {

		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel") ) {
			return "redirect:/admin/employee/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("employee", employee);
			employee.setBusinessentityid(id);
			model.addAttribute("employees", da.getAllEmployee());
			model.addAttribute("persons", da.getAllPersons());
			return "admin/updateEmployee";
		}
		if (!action.equalsIgnoreCase("Cancel") || !action.equalsIgnoreCase("Cancelar")) {
			employee.setBusinessentityid(id);
			da.updateEmployee(employee.getBusinessentityid(), employee);
			model.addAttribute("employees", da.getAllEmployee());
			model.addAttribute("persons", da.getAllPersons());
			
		}
		return "redirect:/admin/employee/";
	}
	
	
	@GetMapping("/admin/employee/")
    public String indexEmployee(Model model) {
		model.addAttribute("employees", da.getAllEmployee());
        return "admin/indexEmployee";
    }
	
	
	@GetMapping("/admin/employee/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee",new Employee());
		model.addAttribute("persons", da.getAllPersons());
		return "admin/addEmployee";
	}
	
	@PostMapping("/admin/employee/add")
	public String saveEmployee(@Validated(BasicInfo.class) @ModelAttribute Employee employee,BindingResult bindingResult,Model model,@RequestParam(value ="action",required = true) String action) {
		
		if (action.equals("Cancelar")) {
			return "redirect:/admin/employee/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("employees", da.getAllEmployee());
			model.addAttribute("persons", da.getAllPersons());
	        return "admin/addEmployee";
		}
		if (!action.equalsIgnoreCase("cancel")) {

			da.createEmploye(employee);
			
		}
		return "redirect:/admin/employee/";
	}
	
	
	
	@GetMapping("/admin/employee/edit/{id}")
	public String showUpdateEmployee(@PathVariable("id") Integer id,Model model) {
		
		Employee employeee = da.getEmployee(id);
		
		if (employeee == null) {
			throw new IllegalArgumentException("Invalid employee Id:" + id);
		}
		model.addAttribute("employee", employeee);
		model.addAttribute("persons", da.getAllPersons());
		return "admin/updateEmployee";
	}
	
	@PostMapping("/admin/employee/edit/{id}")
	public String updateEmployee(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @Validated(BasicInfo.class) @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {

		if (action.equalsIgnoreCase("Cancelar") || action.equalsIgnoreCase("Cancel") ) {
			
			return "redirect:/admin/employee/";
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("employee", employee);
			employee.setBusinessentityid(id);
			model.addAttribute("employees", da.getAllEmployee());
			model.addAttribute("persons", da.getAllPersons());
			return "admin/updateEmployee";
		}
		if (!action.equalsIgnoreCase("Cancel") || !action.equalsIgnoreCase("Cancelar")) {
			
			employee.setBusinessentityid(id);
			da.updateEmployee(employee.getBusinessentityid(), employee);
			model.addAttribute("employees", da.getAllEmployee());
			model.addAttribute("persons", da.getAllPersons());
			
		}
		return "redirect:/admin/employee/";
	}
	
}
