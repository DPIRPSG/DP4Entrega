package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;
import domain.Tax;

import repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private CategoryRepository categoryRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private TaxService taxService;
	
	//Constructors -----------------------------------------------------------
	
	public CategoryService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public Category create(){
		Category result;
		
		result = new Category();
		
		return result;
	}
	
	// Save solo debe usarse para guardar el objeto por primera vez
	public void save(Category category){
		Assert.notNull(category);
		
		categoryRepository.save(category);
	}
	
	public void delete(Category category){
		Assert.notNull(category);
		
		Tax tax;
		
		tax = taxService.findByCategory(category);
		
		if (tax == null){
			categoryRepository.delete(category);
		}else{
			// No se puede borrar ya que está asociado a una tasa
		}
	}
	
	public Collection<Category> findAll(){
		Collection<Category> result;
		
		result = categoryRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------
 
	public Category findByItem(Item item){
		Category result;
		
		result = categoryRepository.findByItemId(item.getId());
		
		return result;
	}
	
	
}
