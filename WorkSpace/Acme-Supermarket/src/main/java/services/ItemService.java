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
	
	//Constructors -----------------------------------------------------------
	
	public ItemService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/** Devuelve Item preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */	
	//req: 12.2
	public Item create(){
		Item result;

		result = new Item();
		result.setDeleted(false);
		
		return result;
	}
	
	/**
	 * Marca como eliminado un item
	 */
	//req: 12.2
	public void delete(Item item){
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		
		item.setDeleted(true);
		this.save(item);
	}
	
	/**
	 * Guarda un item creado o modificado
	 */
	//req: 12.2
	public void save(Item item){
		Assert.notNull(item);
		
		itemRepository.save(item);
	}
	
	// De aqu� para abajo no ha sido necesario
	
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

	//Other business methods -------------------------------------------------

	/**
	 * Devuelve los Items de una categor�a dada.
	 */
	//req: 10.2, 12.4
	public Collection<Item> findAllByCategory(Category category){
		Assert.notNull(category);
		
		Collection<Item> result;
		
		result = itemRepository.findAllByCategoryId(category.getId());
		
		return result;
	}
	
	/**
	 * Devuelve los Items de una categor�a dada. DEVUELVE LOS ELIMINADOS
	 */
	//req: 12.4
	public Collection<Item> findAllNotDeletedByCategory(Category category){
		Assert.notNull(category);
		
		Collection<Item> result;
		
		result = itemRepository.findAllNotDeletedByCategoryId(category.getId());
		
		return result;
	}
	
	/**
	 * Devuelve los items no eliminados pertenecientes a un shoppingCart.
	 */
	// req: 11.2
	public Collection<Item> findAllByShoppingCart(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		
		Collection<Item> result;
		
		result = itemRepository.findAllByShoppingCartId(shoppingCart.getId());
		
		return result;
	}
	
	/**
	 * Devuelve los items que aplican la tax independientemente de si un item est� borrado
	 */
	//req: 12.3
	public Collection<Item> findByTax(Tax tax){
		Collection<Item> result;
		
		result = itemRepository.findByTaxId(tax.getId());
		
		return result;
	}
	
	/**
	 * Lista todos los items de un warehouse
	 */
	//req: 17.3
	public Collection<Item> findAllByWareHouse(WareHouse wareHouse){
		Assert.notNull(wareHouse);
		
		Collection<Item> result;
		
		result = itemRepository.findAllByWareHouseId(wareHouse.getId());
		
		return result;
	}
	
	/**
	 * Encuentra un item a partir de un texto. Dicho texto puede estar en su sku, su nombre o su descripci�n.
	 */
	// req: 10.3	
	public Collection<Item> findBySingleKeyword(String keyword){
		Assert.notNull(keyword);
		Assert.isTrue(!keyword.isEmpty());
		
		Collection<Item> result;
		
		result = itemRepository.findBySingleKeyword(keyword);
		
		return result;
	}
	
	
	
	// De aqu� para abajo no ha sido necesario
		
	public Collection<Item> findAllDeleted(){
			Collection<Item> result;
			
			result = itemRepository.findAll();
			
			return result;
	}

	/**
	 * Lista el item mejor vendido. En caso de igualdad devuelve varios. 
	 * Considera las orders canceladas y no canceladas
	 */
	//req: 12.7.3
	public Collection<Item> findItemBestSelling(){
		Collection<Item> result;
		
		result = itemRepository.findItemBestSelling();
		
		return result;
	}
	
	/**
	 * Lista el item peor vendido. En caso de igualdad devuelve varios. 
	 * Considera las orders canceladas y no canceladas.
	 */
	//req: 12.7.4	
	public Collection<Item> findItemWorstSelling(){
		Collection<Item> result;
		
		result = itemRepository.findItemWorstSelling();
		
		return result;
	}

	/**
	 * Devuelve el/los items con m�s comentarios
	 */
	//ref: 25.2.1
	public Collection<Item> findItemMoreComments(){
		Collection<Item> result;
		
		result = itemRepository.findItemMoreComments();
		
		return result;
	}

}
