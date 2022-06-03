package icesi.edu.co.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.co.DAO.SalestaxRateDao;
import icesi.edu.co.DAO.StateProvinceDao;
import icesi.edu.co.person.Stateprovince;
import icesi.edu.co.repository.SalestaxRateRepo;
import icesi.edu.co.sales.Salestaxrate;


@Service
public class SalesTaxRateServiceimpl implements SalesTaxRateService{


		private SalestaxRateDao salesRepo;
	
		private StateProvinceDao stateRepo;

		@Autowired
		public SalesTaxRateServiceimpl(SalestaxRateDao salesRepo,StateProvinceDao stateRepo) {
			this.salesRepo = salesRepo;
			this.stateRepo = stateRepo;
		}
		
		@Transactional
		@Override
		public Salestaxrate save(Salestaxrate entity, Integer stateprovinceid) {
			
			Salestaxrate aux1 = null;
			
			boolean one = (entity.getTaxrate() != null) && (entity.getTaxrate().doubleValue() >= 0);
			boolean two =  (entity.getName() != null) && entity.getName().length() >= 5;
			

			
			if(one && two) {
				
				Optional<Stateprovince> optional = Optional.ofNullable(this.stateRepo.getByInt(stateprovinceid));
				
				if(optional.isPresent()) {
					
					aux1 = this.salesRepo.save(entity);
				}else {
					return aux1 = null;
				}
				
			}else {
				return aux1 = null;
			}
			
			
			return aux1;
		}
		
		@Transactional
		@Override
		public Salestaxrate update(Salestaxrate entity, Integer stateprovinceid) {
			
			boolean one = (entity.getTaxrate() != null) && (entity.getTaxrate().doubleValue() >= 0);
			boolean two =  (entity.getName() != null) && entity.getName().length() >= 5;

			if(one && two) {
			if(entity.getSalestaxrateid() != null) {
				Optional<Stateprovince> optional = Optional.ofNullable(this.stateRepo.getByInt(stateprovinceid));
				Optional<Salestaxrate> optinalEntity = Optional.ofNullable(this.salesRepo.getByInt(entity.getSalestaxrateid()));
				if(optinalEntity.isPresent() && optional.isPresent()) {
					entity = this.salesRepo.update(entity);
				}else {
					return entity = null;
				}
				
			}
		 }else {
				return entity = null;
			}
			
			
			return entity;
			
		}
		@Transactional
		@Override
		public Optional<Salestaxrate> findByID(Integer id) {
			return Optional.ofNullable(salesRepo.getByInt(id));
		}
		@Transactional
		public Iterable<Salestaxrate> findAll() {
			return salesRepo.getAll();
		}
		@Transactional
		public Salestaxrate getSalestaxrate(Integer id) {
			return salesRepo.getByInt(id);
		}

		@Override
		public void deleteByID(Integer id) {
			
			Salestaxrate str= salesRepo.getByInt(id);
			salesRepo.delete(str);
		}

}
