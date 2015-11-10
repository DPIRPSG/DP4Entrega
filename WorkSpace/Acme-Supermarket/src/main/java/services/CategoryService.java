package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;

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
		
		result = null;
		System.out.println("El método create en CategoryService está incompleto");
		
		return result;
	}
	
	public void save(Category category){
		Assert.isTrue(this.exists(category));
		
	}
	
	public boolean exists(Category category){
		Assert.isNull(category);
		
		boolean result;
		
		result = categoryRepository.exists(category.getId());
		
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
 
}
