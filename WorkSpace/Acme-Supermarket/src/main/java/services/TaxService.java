package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;
import domain.Tax;

import repositories.TaxRepository;

@Service
@Transactional
public class TaxService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private TaxRepository taxRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ItemService itemService;
	
	//Constructors -----------------------------------------------------------
	
	public TaxService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Tax create(){
		Tax result;
		
		result = null;
		
		return result;
	}
	
	public void save(Tax tax){
		Assert.isNull(tax);
		
		taxRepository.save(tax);
	}
	
	public void delete(Tax tax){
		Assert.isNull(tax);
		Assert.isTrue(tax.getId() != 0);
		
		Collection<Item> itemsWithTax;
		
		itemsWithTax = itemService.findByTax(tax);
		
		if(itemsWithTax.isEmpty()){
			taxRepository.delete(tax);
		}else{
			// Ha sido usado por lo que no debe ser eliminado
		}
	}
	
	public Collection<Tax> findAll(){
		Collection<Tax> result;
		
		result = taxRepository.findAll();
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	public Tax findByCategory(Category category){
		Assert.notNull(category);
		
		Tax result;
		
		result = taxRepository.findByCategoryId(category.getId());
		
		return result;
	}
 
}
