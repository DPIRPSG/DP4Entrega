package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Clerk;
import domain.Consumer;
import domain.Order;
import domain.OrderItem;
import domain.ShoppingCart;

import repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

	//Managed repository -----------------------------------------------------

	@Autowired
	private OrderRepository orderRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ConsumerService consumerService;
	
	//Constructors -----------------------------------------------------------
	
	public OrderService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Order create(){
		Order result;
		String ticker;
		
		result = new Order();
		
		ticker = this.tickerGenerate();
		
		result.setPlacementMoment(new Date());
		result.setTicker(ticker);
		
		return result;
	}
	
	public void save(Order order){
		Assert.notNull(order);
		
		Collection<OrderItem> orderItems;
		
		orderRepository.save(order);
		
		orderItems = order.getOrderItems();
		orderItemService.save(orderItems);
	}
	
	
	public Collection<Order> findAll(){
		Collection<Order> result;
		
		result = orderRepository.findAll();
		
		return result;
	}
	

	//Other business methods -------------------------------------------------
	
	public Order createFromShoppingCart(ShoppingCart shoppingCart, Consumer consumer){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		Order result;
		Collection<OrderItem> orderItems;
		double amount;
		
		result = this.create();
		
			// Adding OrderItems
		orderItems = orderItemService.createByShoppingCart(shoppingCart, result);
		result.setOrderItems(orderItems);
		
			// Calculate amount
		amount = this.amountCalculate(orderItems);
		result.setAmount(amount);
		
			// Adding Order to Consumer
		consumer.addOrder(result);
		
		consumerService.save(consumer);
		return result;
	}
	
	private String tickerGenerate(){
		String result;
		
		System.out.println("El método tickerGenerate en OrderService no está completado");
		result = "unknown";
		
		return result;
	}
	
	private double amountCalculate(Collection<OrderItem> orderItems){
		double result;
		
		result = 0.0;
		
		for (OrderItem orderItem : orderItems) {
			result += orderItem.getPrice() * orderItem.getUnits();
		}
		
		return result;
	}
	
	public void cancelOrder(Order order){
		Assert.isNull(order);
		Assert.isTrue(order.getId() != 0);
		
		Clerk clerk;
		
		clerk = clerkService.findByOrder(order);
		
		if(clerk == null){
			order.setCancelMoment(new Date());
			this.save(order);
		}else{
			// No puede borrarse una order que está asignada a un Clerk
		}
		System.out.println("El método cancelOrder en OrderService está incompleto");
	}
	
	public Order assignToClerkAutomatically(Clerk clerk){
		Assert.notNull(clerk);
		
		Order result;
		
		result = this.findAllNotAssigned().iterator().next();
		
		this.assignToClerkManual(clerk, result);
		
		return result;
	}
	
	public void assignToClerkManual(Clerk clerk, Order order){
		Assert.notNull(clerk);
		Assert.notNull(order);
		
		order.setClerk(clerk);
		
		this.save(order);
	}
	
	private Collection<Order> findAllNotAssigned(){
		Collection<Order> result;
		
		//deben estar ordenadas siendo la primera la más antigua
		result = orderRepository.findAllNotAssigned();
		
		return result;
	}
	
	public double rateOrderCancelled(){
		double result;
		
		result = orderRepository.rateOrderCancelled();
		
		return result;
	}


	
}
