package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Consumer;

import repositories.ConsumerRepository;

@Service
@Transactional
public class ConsumerService {
	//Managed repository -----------------------------------------------------
	
	private ConsumerRepository consumerRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------

	public ConsumerService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	//Other business methods -------------------------------------------------

	public Collection<Consumer> findConsumerMoreOrders(){
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerMoreOrders();
		
		return result;
	}
	
	public Collection<Consumer> findConsumerSpentMoreMoney(){
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerSpentMoreMoney();
		
		return result;
	}

	public Collection<Consumer> findConsumerMoreOrdersCancelled(){
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerMoreOrdersCancelled();
		
		return result;
	}
	
	public Collection<Consumer> findConsumerLessOrdersCancelled(){
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerLessOrdersCancelled();
		
		return result;
	}

}
