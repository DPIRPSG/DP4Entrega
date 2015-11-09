package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Content;
import domain.Item;
import domain.ShoppingCart;

import repositories.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartService {

 	//Managed repository -----------------------------------------------------

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ContentService contentService;
	
	//Constructors -----------------------------------------------------------

	public ShoppingCartService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public boolean exists(ShoppingCart shoppingCart){
		boolean result;
		
		result = shoppingCartRepository.exists(shoppingCart.getId());
		
		return result;
	}
	//Other business methods -------------------------------------------------
 
	public Collection<Item> ListAllContain(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		
		Collection<Item> result;
		
		result = shoppingCartRepository.findAllContainByShoppingCartId(shoppingCart.getId());
		
		return result;
	}
	
	public void AddItem(ShoppingCart shoppingCart, Item item){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCartRepository.exists(shoppingCart.getId()));
		Assert.notNull(item);
		Assert.isTrue(itemService.exists(item));
		
		Content content;
		
		content = contentService.findByShoppingCartAndItem(shoppingCart, item);
		
		System.out.println("Crear content dentro de ShoppingCart no realizado");
		if (content == null) {
			// Crear content
		}else{
			int units = content.getUnits();
			content.setUnits(units + 1);
		}
		contentService.save(content);
		// Añadir a un shoppingCart el item
		System.out.println("El método AddItem de ShoppingCartItem no está implementado");
	}
	
	public void ChangeItemQuantity(ShoppingCart shoppingCart, Item item, int quantity){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCartRepository.exists(shoppingCart.getId()));
		Assert.notNull(item);
		Assert.isTrue(itemService.exists(item));
		
		Content content;
		System.out.println("El método AddItem de ChangeItemQuantity no está implementado");

		
	}
}
