package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;
import domain.ShoppingCart;
import domain.Tax;
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
		
		return result;
	}
	
	public void delete(Item item){
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		
		item.setDeleted(true);
		this.save(item);
	}
	
	public void save(Item item){
		Assert.notNull(item);
		
		itemRepository.save(item);
	}
	
	// De aquí para abajo no ha sido necesario
	
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

	public Collection<Item> findAllByCategory(Category category){
		Assert.notNull(category);
		
		Collection<Item> result;
		
		result = itemRepository.findAllByCategoryId(category.getId());
		
		return result;
	}
	
	public Collection<Item> findAllByShoppingCart(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		
		Collection<Item> result;
		
		result = itemRepository.findAllByShoppingCartId(shoppingCart.getId());
		
		return result;
	}
	
	public Collection<Item> findByTax(Tax tax){
		Collection<Item> result;
		
		// No ignorar los eliminados
		result = itemRepository.findByTaxId(tax.getId());
		
		return result;
	}

	
	// De aquí para abajo no ha sido necesario
	
	
	
	public Collection<Item> findAllDeleted(){
			Collection<Item> result;
			
			result = itemRepository.findAll();
			
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
