package services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Consumer;
import domain.Content;
import domain.CreditCard;
import domain.Item;
import domain.Order;
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
	
	@Autowired
	private OrderService orderService;
	
	//Constructors -----------------------------------------------------------

	public ShoppingCartService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	private void save(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		
		shoppingCartRepository.save(shoppingCart);
	}
	
	//No usados
	
	public boolean exists(ShoppingCart shoppingCart){
		boolean result;
		
		result = shoppingCartRepository.exists(shoppingCart.getId());
		
		return result;
	}
	
	
	
	//Other business methods -------------------------------------------------
 
	/**
	 * Crea un Order al que solo se le deben añadir los últimos datos y guardarse en la base de datos.
	 * 
	 * Ninguno de los elementos creados son persistidos en la base de datos
	 */
	public Order createCheckOut(Consumer consumer){
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		
		Order result;
		ShoppingCart shoppingCart;
		
		shoppingCart = this.findByConsumer(consumer);		
		
			// Create a order with their orderItems (doesn't save the order) 
		result = orderService.createFromShoppingCart(shoppingCart, consumer);

		return result;
	}
	
	/**
	 * Guarda el objeto creado con createCheckOut
	 */
	public void saveCheckOut(Order order, Consumer consumer){
		Assert.notNull(order);
		
		orderService.save(order);
		this.emptyShoppingCart(consumer);
	}
	
	/**
	 * Vacia el carrito
	 * 
	 */
	private void emptyShoppingCart(Consumer consumer){
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		
		ShoppingCart shoppingCart;
		
		shoppingCart = this.findByConsumer(consumer);
		
		shoppingCart.emptyComments();
		contentService.emptyByShoppingCart(shoppingCart);
		
		this.save(shoppingCart);
	}

	
	public ShoppingCart findByConsumer(Consumer consumer){
		ShoppingCart result;
		
		result = shoppingCartRepository.findByConsumerId(consumer.getId());
		
		return result;
	}
	
	public void AddItemQuantity(ShoppingCart shoppingCart, Item item){
		contentService.createByShoppingCartAndItem(shoppingCart, item);
	}
	
	public void ChangeItemQuantity(ShoppingCart shoppingCart, Item item, int quantity){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, quantity);
	}
	
	public void DeleteItemQuantity(ShoppingCart shoppingCart, Item item){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, 0);
	}
	
	public void AddComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		shoppingCart.addComment(comment);
		shoppingCartRepository.save(shoppingCart);
	}
	
	public void deleteComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		System.out.println("El método deleteComment en ShoppingCartService no está implementado");
	}
	
	public Order checkOut(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		Order result;
		
		result = orderService.createFromShoppingCart(shoppingCart);
		
		orderService.save(result);
		
		return result;
		
	}
}
