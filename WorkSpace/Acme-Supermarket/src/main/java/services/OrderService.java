package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	//Other business methods -------------------------------------------------
	
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
