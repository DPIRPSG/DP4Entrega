package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	
	public Consumer create(){
		Consumer result;
		
		result = new Consumer();

		return result;
	}
	
	public void save(Consumer consumer){
		Assert.isNull(consumer);
		
		consumerRepository.save(consumer);
	}
	
	public Collection<Consumer> findAll(){
		Collection<Consumer> result;
		
		result = consumerRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------
	
	
	//No usado hacia abajo
	
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
