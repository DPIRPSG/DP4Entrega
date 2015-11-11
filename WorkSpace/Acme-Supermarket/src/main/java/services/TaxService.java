package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Tax;

import repositories.TaxRepository;

@Service
@Transactional
public class TaxService {
	//Managed repository -----------------------------------------------------

	private TaxRepository taxRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------
	
	public TaxService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Tax create(){
		Tax result;
		
		result = null;
		System.out.println("El método create dentro de la clase TaxService está incompleto");
		
		return result;
	}
	
	public void save(Tax tax){
		Assert.isNull(tax);
		Assert.isTrue(this.exists(tax));
		
		taxRepository.save(tax);
	}
	
	public void delete(Tax tax){
		Assert.isNull(tax);
		Assert.isTrue(this.exists(tax));
		
		System.out.println("El método delete dentro de TaxService esta incompleto");
	}
	
	public boolean exists(Tax tax){
		boolean result;
		
		result = taxRepository.exists(tax.getId());
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
 
}
