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
	
	/** Devuelve Order solo con ticker. Necesita añadir OrderItems y usar save para que persista en la base de datos.
	 * 
	 */	
	//req: 11.7
	private Order create(){
		Order result;
		String ticker;
		
		result = new Order();
		
		ticker = this.tickerGenerate();
		
		result.setPlacementMoment(new Date());
		result.setTicker(ticker);
		
		return result;
	}
	/**
	 * Guarda o actualiza una order
	 */
	//req: 11.7
	public void save(Order order){
		Assert.notNull(order);
		
		Collection<OrderItem> orderItems;
		
		orderItems = order.getOrderItems();
		orderItemService.save(orderItems);
		
		orderRepository.save(order);
	}
	
	/**
	 * Lista todas las orders guardadas en el sistema.
	 */
	//req: 12.6
	public Collection<Order> findAll(){
		Collection<Order> result;
		
		result = orderRepository.findAll();
		
		return result;
	}
	

	//Other business methods -------------------------------------------------
	
	/**
	 * Crea una Order desde ShoppingCart. NO USAR. Usar desde ShoppingCartService.createCheckOut.
	 */
	//req: 11.7
	public Order createFromShoppingCart(ShoppingCart shoppingCart, Consumer consumer){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(consumer);
		Assert.isTrue(consumer.getId() != 0);
		
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
	
	/**
	 * 	Genera un ticker cumpliendo el Pattern
	 */
	//req: 11.7
	private String tickerGenerate(){
		String result;
		/*UUID codeUUID;
		String code;
		String[] ticker;
		String definitedTicker;
		UUIDGenerator generator;
		Pattern pattern;
		
		pattern = Pattern.compile("regular expresion here");
		
		
		
		generator.
		codeUUID = UUIDGenerator.randomUUID();
		code = codeUUID.toString();
		ticker = code.split("-");
		definitedTicker = ticker[0]+"-"+ticker[1];*/
		System.out.println("El método tickerGenerate en OrderService no está completado");
		result = "000000-unkn";
		
		return result;
	}
	
	/**
	 * Calcula el precio de los orders
	 */
	// req: 11.7
	private double amountCalculate(Collection<OrderItem> orderItems){
		Assert.notEmpty(orderItems);
		
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
