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

import icesi.edu.co.person.Address;
import icesi.edu.co.person.Countryregion;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.repository.AddressRepo;
import icesi.edu.co.repository.CountryRegionRepo;
import icesi.edu.co.repository.SalestaxRateRepo;
import icesi.edu.co.repository.StateProvinceRepo;
import icesi.edu.co.sales.Salestaxrate;
import icesi.edu.co.DAO.AddressDao;

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
	public CommandLineRunner add(CountryRegionRepo crp, AddressRepo adr, StateProvinceRepo spr, SalestaxRateRepo srp) {
		return (args) -> {
			
			
//			Countryregion cr = new Countryregion();
//			cr.setCountryregioncode("250");
//			cr.setName("Colombia");
//			crp.save(cr);
////			
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Valle del Cauca");
			spr.save(state);
////			
////			
//			Address adres = new Address();
//			adres.setAddressline1("Cra 29 #10b - 118");
//			adres.setAddressline2("Cra 29 #10b - 119");
//			adres.setCity("Cali");
//			adres.setPostalcode("123456");
//			adres.setStateprovince(state);
//			adr.save(adres);
//			
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			srp.save(sales);
//			
			
		};
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
