package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javassist.expr.NewArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.ShoppingCart;
import domain.Tax;

import repositories.OrderItemRepository;

@Service
@Transactional
public class OrderItemService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TaxService taxService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	//Constructors -----------------------------------------------------------

	public OrderItemService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve OrderItem preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */	
	//req: 11.7
	public OrderItem create(){
		OrderItem result;
		
		result = new OrderItem();
		
		return result;
	}
	
	public void save(OrderItem orderItem){
		Assert.notNull(orderItem);
		
		orderItemRepository.save(orderItem);
	}
	
	/**
	 * Guarda o actualiza muchos orderItems
	 */
	//req: 11.7
	public void save(Collection<OrderItem> orderItems){
		Assert.notNull(orderItems);

		orderItemRepository.save(orderItems);
	}

	//Other business methods -------------------------------------------------
	
	/**
	 * NO USAR. Usar desde ShoppingCartService.createCheckOut. Devuelve los orders items creados pero no alamacenados.
	 */
	// req: 11.7
	public Collection<OrderItem> createByShoppingCart(ShoppingCart shoppingCart, Order order){
		Assert.notNull(shoppingCart);
		// Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(order);
		// La order no está creada por lo que no se puede chequear el id
		
		Set<OrderItem> result;
		Collection<OrderItem> result2 = null;
		Collection<Item> items;
		OrderItem orderItem;
		
		int units;
		result = new HashSet<OrderItem>();
		result2 = new ArrayList<OrderItem>();
			// Debe devolver los items no borrados del sistema
		items = itemService.findAllByShoppingCart(shoppingCart);
		
		Assert.notEmpty(items, "Can't create OrderItems if the shoppingCart is empty");
		
		System.out.println("OrderItemService -> createByShoppingCart -> orderItems creados");
		for (Item item : items) {
			units = shoppingCartService.consultItemQuantity(shoppingCart, item);			
			orderItem = this.createByShoppingCart(item, order, units);
			System.out.println(orderItem.toString()+": "+orderItem.getName()+" x "+orderItem.getUnits());
			result.add(orderItem);
			result2.add(orderItem);
		}	
		
		//result2.addAll(result);
		
		System.out.println("Another");
		for (OrderItem o: result2){
			System.out.println(o.toString()+ ": "+o.getName()+", "+o.getPrice()+"e x "+o.getUnits()+" units");

		}
		
		
		return result2;
	}
	
	/**
	 * Crea un OrderItem dado un item. No se almacena.
	 */
	//req: 11.7
	private OrderItem createByShoppingCart(Item item, Order order, int units){
		OrderItem result;
		Tax tax;
		Category category;

		category = categoryService.findByItem(item);
		tax = taxService.findByCategory(category);
		
		result = this.create();
		result.setSku(item.getSku());
		result.setName(item.getName());
		result.setDescription(item.getDescription());
		result.setPrice(item.getPrice());
		result.setTags(item.getTags());
		result.setDeleted(item.getDeleted());
		result.setTax(tax.getValue());
		result.setNameCategory(category.getName());
		result.setUnits(units);
		result.setOrder(order);
		
		return result;
	}
	

	
}
