package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;

import repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	//Managed repository -----------------------------------------------------

	private CategoryRepository categoryRepository;
	
	//Supporting services ----------------------------------------------------

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
		Assert.isTrue(!this.exists(category));
		
		System.out.println("El método save en CategoryService no tiene en cuenta la concurrencia");
		categoryRepository.save(category);
	}
	
	public boolean exists(Category category){
		Assert.isNull(category);
		
		boolean result;
		
		result = categoryRepository.exists(category.getId());
		
		return result;
	}
	
	public Category update(Category category){
		Assert.isTrue(this.exists(category));
		
		Category result;
		
		System.out.println("El método update en CategoryService no tiene en cuenta la concurrencia");
		result = categoryRepository.save(category);
		
		return result;
	}
	
	public void delete(Category category){
		Assert.isTrue(this.exists(category));
		
		System.out.println("El método delete dentro de CategoryService está incompleto");
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
