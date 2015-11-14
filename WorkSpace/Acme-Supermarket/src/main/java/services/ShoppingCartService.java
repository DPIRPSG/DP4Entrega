package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Consumer;
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
	private ContentService contentService;
	
	@Autowired
	private OrderService orderService;
	
	//Constructors -----------------------------------------------------------

	public ShoppingCartService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/**
	 * Guarda los cambios del carrito. Usar solo para comentarios.
	 */
	//req: 11.6
	public void save(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		
		shoppingCartRepository.save(shoppingCart);
	}
		
	
	//Other business methods -------------------------------------------------
 
	/**
	 * Crea un Order al que solo se le deben añadir los últimos datos y guardarse en la base de datos.
	 * 
	 * Ninguno de los elementos creados son persistidos en la base de datos
	 */
	// req: 11.7
	public Order createCheckOut(Consumer consumer){
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		
		Order result;
		ShoppingCart shoppingCart;
		
		shoppingCart = this.findByConsumer(consumer);		
		
			// Create a order with their orderItems (none is persist) 
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
	
	/**
	 * Devuelve el carrito de un consumer.
	 */
	//req: 11.2, 11.7
	public ShoppingCart findByConsumer(Consumer consumer){
		Assert.notNull(consumer);
		
		ShoppingCart result;
		
		result = shoppingCartRepository.findByConsumerId(consumer.getId());
		
		return result;
	}
	
	/**
	 * 
	 * Dice la cantidad de un Item en un carrito
	 */
	// req: 11.2, 11.7
	public int consultItemQuantity(ShoppingCart shoppingCart, Item item){
		int result;
		
		result = contentService.quantityByShoppingCartAndItem(shoppingCart, item);
		
		return result;
	}
	
	/**
	 * Añade un Item a un carrito
	 */
	//req: 11.3
	public void addItem(ShoppingCart shoppingCart, Item item){
		contentService.createByShoppingCartAndItem(shoppingCart, item);
	}

	/**
	 * Cambia la cantidad de Items en un carrito
	 */
	//req: 11.4
	public void changeItemQuantity(ShoppingCart shoppingCart, Item item, int quantity){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, quantity);
	}
	
	/**
	 * Cambia la cantidad de Items en un carrito
	 */
	//req: 11.5
	public void deleteItemQuantity(ShoppingCart shoppingCart, Item item){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, 0);
	}
	
	public void addComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		shoppingCart.addComment(comment);
		shoppingCartRepository.save(shoppingCart);
	}
	
	public void deleteComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		System.out.println("El método deleteComment en ShoppingCartService no está implementado");
	}
}
