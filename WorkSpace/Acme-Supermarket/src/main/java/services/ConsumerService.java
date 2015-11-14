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
	
	/** Devuelve consumer preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */
	// req: 10.1
	public Consumer create(){
		Consumer result;
		
		result = new Consumer();

		return result;
	}
	
	/**
	 * Almacena en la base de datos el cambio
	 */
	// req: 10.1
	public void save(Consumer consumer){
		Assert.isNull(consumer);
		
		consumerRepository.save(consumer);
	}
	
	/**
	 * Lista los consumers registrados
	 */
	// req: 12.5
	public Collection<Consumer> findAll(){
		Collection<Consumer> result;
		
		result = consumerRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------
	
	/**
	 * Lista el consumers con más orders. En caso de igualdad devuelve varios. 
	 * Cuenta las orders canceladas y las no canceladas
	 */
	//req: 12.7.1
	public Collection<Consumer> findConsumerMoreOrders(){
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumerMoreOrders();
		
		return result;
	}

	/**
	 * Lista el consumers que ha gastado más dinero. En caso de igualdad devuelve varios. 
	 * Solo considera las orders no canceladas
	 */
	//req: 12.7.2
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
