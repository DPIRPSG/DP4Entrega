package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;
import domain.ShoppingCart;
import domain.WareHouse;

import repositories.ItemRepository;

@Service
@Transactional
public class ItemService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private ItemRepository itemRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private WareHouseService wareHouseService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//Constructors -----------------------------------------------------------
	
	public ItemService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Item create(){
		Item result;

		result = new Item();
		
		System.out.println("El método create dentro de ItemService no está finalizado");
		
		return result;
	}
	
	public void delete(Item item){
		Assert.isTrue(this.exists(item));
		
		item.setDeleted(true);
		this.update(item);
		
	}
	
	public Item save(Item item){
		Assert.isTrue(!this.exists(item));
		
		Item result;
		
		System.out.println("El método save en ItemService no tiene en cuenta la concurrencia");
		result = itemRepository.save(item);
		
		return result;
	}
	
	public Item update(Item item){
		Assert.isTrue(this.exists(item));
		
		Item result;
		
		System.out.println("El método update en ItemService no tiene en cuenta la concurrencia");
		result = itemRepository.save(item);
		
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


	
	public boolean exists(Item item){
		Assert.isNull(item);
		
		boolean result;
		
		result = itemRepository.exists(item.getId());
		
		return result;
	}

	
	//Other business methods -------------------------------------------------
	
	public Collection<Item> findAllDeleted(){
			Collection<Item> result;
			
			result = itemRepository.findAll();
			
			return result;
	}
	
	public Collection<Item> findAllByShoppingCart(ShoppingCart shoppingCart){
		Assert.isTrue(shoppingCartService.exists(shoppingCart));
		
		Collection<Item> result;
		
		result = itemRepository.findAllByShoppingCartId(shoppingCart.getId());
		
		return result;
	}
	
	public Collection<Item> findAllByCategory(Category category){
		Collection<Item> result;
		
		result = itemRepository.findAllByCategoryId(category.getId());
		
		return result;
	}
	
	public Collection<Item> findAllByWareHouse(WareHouse wareHouse){
		Assert.isTrue(wareHouseService.exists(wareHouse));
		
		Collection<Item> result;
		
		//si lo necesitas se puede cambiar el codigo para que se ejecute desde content y facilitar el trabajo
		result = itemRepository.findAllByWareHouseId(wareHouse.getId());
		
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
