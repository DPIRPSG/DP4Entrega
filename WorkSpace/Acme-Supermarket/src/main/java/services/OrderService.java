package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Clerk;
import domain.Order;

import repositories.OrderRepository;

@Service
@Transactional
public class OrderService {

	//Managed repository -----------------------------------------------------

	private OrderRepository orderRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ClerkService clerkService;
	
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
	
	public void save(Order order){
		Assert.isTrue(this.exists(order));
		
		orderRepository.save(order);
	}

	//Other business methods -------------------------------------------------
	
	public boolean cancelOrder(Order order){
		Assert.isTrue(this.exists(order));
		
		boolean result;
		
		result = false;
		System.out.println("El método cancelOrder en OrderService está incompleto");
		
		return result;
	}
	
	public Order assignToClerk(Clerk clerk){
		Assert.isTrue(clerkService.exists(clerk));
		
		Order result;
		
		result = this.findAllNotAssigned().iterator().next();
		
		result.setClerk(clerk);
		
		this.save(result);
		
		return result;
		
	}
	
	public double rateOrderCancelled(){
		double result;
		
		result = orderRepository.rateOrderCancelled();
		
		return result;
	}

	public Collection<Order> findAllNotAssigned(){
		Collection<Order> result;
		
		//deben estar ordenadas siendo la primera la más antigua
		result = orderRepository.findAllNotAssigned();
		
		return result;
	}
	
	
}
