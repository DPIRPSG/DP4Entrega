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

	private Integer ticker = 0;
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
		System.out.println("Creo la order");
		ticker = this.tickerGenerate();
		System.out.println("Generó el ticker");
		result.setPlacementMoment(new Date());
		result.setTicker(ticker);
		System.out.println("Cambio el ticker y la fecha");
		
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
		
		orderRepository.saveAndFlush(order);
		System.out.println("ha guardado el order");
		orderItemService.save(orderItems);
		System.out.println("ha guardado los orderItems");
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
		
		System.out.println("Paso las restricciones en OrderService.createFromShoppingCart");
		Order result;
		Collection<OrderItem> orderItems;
		double amount;
		
		result = this.create();
		
		System.out.println("Create en orderService llamado al crete");
			// Adding OrderItems
		orderItems = orderItemService.createByShoppingCart(shoppingCart, result);
		result.setOrderItems(orderItems);
		System.out.println("OrderItems en OrderService añadidos");
		
			// Calculate amount
		amount = this.amountCalculate(orderItems);
		result.setAmount(amount);
		System.out.println("Amount calculaado correctamente en OrderService");
		
			// Adding Order to Consumer
		consumer.addOrder(result);
		
		System.out.println("Añadidos los orders al consumer");
		
		consumerService.save(consumer);
		System.out.println("saliendo de createFromShoppingCart en OrderService");
		return result;
	}
	
	/**
	 * 	Genera un ticker cumpliendo el Pattern
	 */
	//req: 11.7
	private String tickerGenerate(){
		String result;
		String tickerNumber;
		String tickerAleatory;
		
		tickerNumber = calculaTickerNumber();
		tickerAleatory = calculaTickerAleatory();
		result = tickerNumber + "-" + tickerAleatory;
		
		if(!compareTicker(result)) {
			result = tickerGenerate();
		}
		
		return result;
	}
	
	private String calculaTickerNumber() {
		String result;
		
		if(ticker < 10) {
			result = "00000" + ticker.toString();
			ticker++;
		} else if(ticker < 100) {
			result = "0000" + ticker.toString();
			ticker++;
		} else if(ticker < 1000) {
			result = "000" + ticker.toString();
			ticker++;
		} else if(ticker < 10000) {
			result = "00" + ticker.toString();
			ticker++;
		} else if(ticker < 100000) {
			result = "0" + ticker.toString();
			ticker++;
		} else if(ticker < 1000000) {
			result = ticker.toString();
			ticker++;
		} else {
			ticker = 0;
			result = calculaTickerNumber();
		}
		
		return result;
	}
	
	private String calculaTickerAleatory() {
		String result;
		char[] conjunto = new char[8];

		char[] elementos={'0','1','2','3','4','5','6','7','8','9' ,'A',
				'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
				'U','V','W','X','Y','Z'};
		
		for(int i=0;i<8;i++){
			int el = (int)(Math.random()*36);
			conjunto[i] = (char)elementos[el];
		}
		result = new String(conjunto);
		
		return result;
	}
	
	private Boolean compareTicker(String ticker) {
		Boolean result;
		Order order;
		
		order = orderRepository.findByTicker(ticker);
		result = (order == null);
		
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
	
	/**
	 * Marca como cancelada una order
	 */
	//req: 16.1
	public void cancelOrder(Order order){
		Assert.notNull(order);
		Assert.isTrue(order.getId() != 0);
		
		Clerk clerk;
		
		clerk = clerkService.findByOrder(order);
		
		Assert.notNull(clerk, "Can't remove a order when a clerk has assigned");
		
		order.setCancelMoment(new Date());
		this.save(order);
	}
	
	/**
	 * Asigna la order mas antigua a un clerk
	 */
	//ref: 18.3
	public Order assignToClerkAutomatically(Clerk clerk){
		Assert.notNull(clerk);
		
		Order result;
		
		result = this.findAllNotAssigned().iterator().next();
		
		this.assignToClerkManual(clerk, result);
		
		return result;
	}

	/**
	 * Asigna la order a un clerk
	 */
	//ref: 18.3
	public void assignToClerkManual(Clerk clerk, Order order){
		Assert.notNull(clerk);
		Assert.isTrue(clerk.getId() != 0);
		Assert.notNull(order);
		Assert.isTrue(order.getId() != 0);
		
		order.setClerk(clerk);
		
		this.save(order);
	}
	
	/**
	 * Devuelven las orders no asignadas a ningún clerk siendo la primera la más antigua
	 */
	//ref: 18.3
	private Collection<Order> findAllNotAssigned(){
		Collection<Order> result;
		
		//deben estar ordenadas siendo la primera la más antigua
		result = orderRepository.findAllNotAssigned();
		
		return result;
	}
	
	/**
	 * El ratio de orders canceladas durante el mes actual
	 */
	//ref: 17.6.5
	public double rateOrderCancelled(){
		double result;
		
		result = orderRepository.rateOrderCancelled();
		
		return result;
	}


	
}
