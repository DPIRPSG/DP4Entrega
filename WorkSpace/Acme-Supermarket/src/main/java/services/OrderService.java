package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Order;

import repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

	//Managed repository -----------------------------------------------------

	private OrderRepository orderRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------
	
	public OrderService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Collection<Order> findAll(){
		Collection<Order> result;
		
		result = orderRepository.findAll();
		
		return result;
	}
	
	public boolean exists(Order order){
		Assert.isNull(order);
		
		boolean result;
		
		result = orderRepository.exists(order.getId());
		
		return result;
	}

	//Other business methods -------------------------------------------------
	
	public boolean cancelOrder(Order order){
		Assert.isTrue(this.exists(order));
		
		boolean result;
		
		result = false;
		System.out.println("El método cancelOrder en OrderService está incompleto");
		
		return result;
	}
	
	public double rateOrderCancelled(){
		double result;
		
		result = orderRepository.rateOrderCancelled();
		
		return result;
	}

	public Collection<Order> findAllNotAssigned(){
		Collection<Order> result;
		
		result = orderRepository.findAllNotAssigned();
		
		return result;
	}
	
	
}
