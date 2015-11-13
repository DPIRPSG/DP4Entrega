package services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import repositories.OrderRepository;

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
	private ContentService contentService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TaxService taxService;

	//Constructors -----------------------------------------------------------

	public OrderItemService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public OrderItem create(){
		OrderItem result;
		
		result = new OrderItem();
		
		return result;
	}
	
	public void save(OrderItem orderItem){
		Assert.notNull(orderItem);
		
		orderItemRepository.save(orderItem);
	}
	
	public void save(Collection<OrderItem> orderItems){
		orderItemRepository.save(orderItems);
	}

	//Other business methods -------------------------------------------------
	
	public Collection<OrderItem> createByShoppingCart(ShoppingCart shoppingCart, Order order){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		Collection<OrderItem> result;
		Collection<Item> items;
		OrderItem orderItem;
		int units;
		
		result = Collections.emptySet();
		items = itemService.findAllByShoppingCart(shoppingCart);
		
		for (Item item : items) {
			units = contentService.contentByShoppingCartAndItem(shoppingCart, item);			
			orderItem = this.createByShoppingCart(item, order, units);
			result.add(orderItem);
		}	
		
		return result;
	}
	
	private OrderItem createByShoppingCart(Item item, Order order, int units){
		OrderItem result;
		Tax tax;
		Category category;

		category = categoryService.findByItem(item);
		tax = taxService.findByCategory(category);
		
		result = create();
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
		
		// this.save(result);
		
		return result;
	}
	

	
}
