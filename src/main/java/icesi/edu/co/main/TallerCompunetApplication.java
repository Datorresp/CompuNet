package icesi.edu.co.main;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import icesi.edu.co.hr.Employee;
import icesi.edu.co.person.Address;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Person;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.repository.AddressRepo;
import icesi.edu.co.repository.CountryRegionRepo;
import icesi.edu.co.repository.EmployeeRepo;
import icesi.edu.co.repository.PersonRepo;
import icesi.edu.co.repository.SalestaxRateRepo;
import icesi.edu.co.repository.StateProvinceRepo;
import icesi.edu.co.sales.Salestaxrate;

@SpringBootApplication
@EnableJpaRepositories("icesi.edu.co.repository")
@EntityScan(basePackages = {"icesi.edu.co.authentication","icesi.edu.co.person","icesi.edu.co.hr"
,"icesi.edu.co.prchasing","icesi.edu.co.prod","icesi.edu.co.sales"})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"icesi.edu.co.authentication","icesi.edu.co.controllers", "icesi.edu.co.repositories", "icesi.edu.co.services", "icesi.edu.co.DAOService", "icesi.edu.co.DAO", "icesi.edu.co.rest"})

public class TallerCompunetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TallerCompunetApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner add(CountryRegionRepo crepo, AddressRepo arepo, StateProvinceRepo spr, SalestaxRateRepo srp, PersonRepo pr, EmployeeRepo ep) {
		return (args) -> {
			
			
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("57");
			cr.setName("Colombia");
			crepo.save(cr);
			
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("123");
			state.setIsonlystateprovinceflag("Y");
			state.setName("CUNDINAMARCA");
			state.setCountryregion(cr);
			spr.save(state);
			
			
			Address adres = new Address();
			adres.setAddressline1("cll 14");
			adres.setAddressline2("# 83 - 50");
			adres.setCity("Bogota");
			adres.setPostalcode("1234");
			adres.setStateprovince(state);
			
			arepo.save(adres);
			
			
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			sales.setStateprovince(state);
			srp.save(sales);
			
			Person persona = new Person();
			persona.setFirstname("Juan Jose");
			persona.setLastname("Restrepo");
			pr.save(persona);
			
//			
//			Employee e = new Employee();
//			e.setNationalidnumber("1005976323");
//			e.setJobtitle("Ing de sistemas");
//			e.setPerson(persona);
//			ep.save(e);
			
		};
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
