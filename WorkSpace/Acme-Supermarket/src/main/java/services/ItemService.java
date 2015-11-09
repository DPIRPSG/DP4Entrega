package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;

import repositories.ItemRepository;

@Service
@Transactional
public class ItemService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private ItemRepository itemRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------
	
	public ItemService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Item create(){
		Item result;
		
		result = null;
		System.out.println("El método create dentro de ItemService no está finalizado");
		
		return result;
	}
	
 	public Item findOne(int itemId) {
		Item result;
		
		result = itemRepository.findOne(itemId);
		Assert.notNull(result, "Item " + itemId + " don't exist");
		
		return result;
	}
	
	public Collection<Item> findAll(){
		Collection<Item> result;
		
		result = itemRepository.findAllNotDeleted();
		
		return result;		
	}

	public void delete(Item item){
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		Assert.isTrue(this.exists(item));
		
		item.setDeleted(true);
		
	}
	
	public void save(Item item){
		Assert.notNull(item);
		Assert.isTrue(this.exists(item));
		
		itemRepository.save(item);
	}
	
	public boolean exists(Item item){
		boolean result;
		
		result = itemRepository.exists(item.getId());
		
		return result;
	}
	
	//crete
	//update

	
	//Other business methods -------------------------------------------------
	
	public Collection<Item> findAllDeleted(){
			Collection<Item> result;
			
			result = itemRepository.findAll();
			
			return result;
	}
	
	public Collection<Item> findAllByCategory(Category category){
		Collection<Item> result;
		
		result = itemRepository.findAllByCategoryId(category.getId());
		
		return result;
	}
	
	public Collection<Item> findByKeyword(String keyword){
		Collection<Item> result;
		
		result = null;
		System.out.println("El método findByKeyword de ItemService no está implementado");
		
		return result;
	}

	public Collection<Item> findItemBestSelling(){
		Collection<Item> result;
		
		result = itemRepository.findItemBestSelling();
		
		return result;
	}
	
	public Collection<Item> findItemWorstSelling(){
		Collection<Item> result;
		
		result = itemRepository.findItemWorstSelling();
		
		return result;
	}

	public Collection<Item> findItemMoreComments(){
		Collection<Item> result;
		
		result = itemRepository.findItemMoreComments();
		
		return result;
	}

}
