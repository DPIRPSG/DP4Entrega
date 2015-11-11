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
		// Crear UserAccount y carpetas para asociarselo
		System.out.println("El método create dentro de ConsumerService está incompleto");
		
		return result;
	}
	
	public Consumer save(Consumer consumer){
		Assert.isTrue(!this.exists(consumer));
		
		Consumer result;
		
		System.out.println("El método save en Consumer Service no comprueba la concurrencia");
		result = consumerRepository.save(consumer);
		
		return result;
	}
	
	public Consumer update(Consumer consumer){
		Assert.isTrue(this.exists(consumer));
		
		Consumer result;
		
		System.out.println("El método update en Consumer Service no comprueba la concurrencia");
		result = consumerRepository.save(consumer);
		
		return result;
	}
	
	public boolean exists(Consumer consumer){
		Assert.isNull(consumer);
		
		boolean result;
		
		result = consumerRepository.exists(consumer.getId());
		
		return result;
	}
	
	public Collection<Consumer> findAll(){
		Collection<Consumer> result;
		
		result = consumerRepository.findAll();
		
		return result;
	}

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
