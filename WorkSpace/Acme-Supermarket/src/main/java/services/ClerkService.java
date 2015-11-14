package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Clerk;
import domain.Order;

import repositories.ClerkRepository;

@Service
@Transactional
public class ClerkService {
	//Managed repository -----------------------------------------------------

	@Autowired
	private ClerkRepository clerkRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------

	public ClerkService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve create preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */	
	//req: 17.1
	public Clerk create(){
		Clerk result;
		
		result = new Clerk();
		
		return result;
	}

	/** 
	 * Guarda un clerk creado o modificado
	 */	
	//req: 17.1
	public void save(Clerk clerk){
		Assert.notNull(clerk);
		
		clerkRepository.save(clerk);
	}

	//Other business methods -------------------------------------------------

	/**
	 * Encuentra un clerk dada la order
	 */
	//req: 16.1
	public Clerk findByOrder(Order order){
		Assert.notNull(order);
		
		Clerk result;
		
		result = clerkRepository.findByOrderId(order.getId());
		
		return result;
	}
	
	/**
	 * Encuentra el/los clerk con más order
	 */
	//req: 17.6.1
	public Collection<Clerk> findClerkServerMoreOrders(){
		Collection<Clerk> result;
		
		result = clerkRepository.findClerkServerMoreOrders();
		
		return result;
	}
	
	/**
	 * Encuentra el/los clerk con menos order
	 */
	//req: 17.6.2
	public Collection<Clerk> findClerkServerLessOrders(){
		Collection<Clerk> result;
		
		result = clerkRepository.findClerkServerLessOrders();
		
		return result;
	}
	


}
