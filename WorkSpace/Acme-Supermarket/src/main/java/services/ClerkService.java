package services;

import java.util.Collection;

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

	private ClerkRepository clerkRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------

	public ClerkService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Clerk create(){
		Clerk result;
		
		result = new Clerk();
		
		return result;
	}
	
	public boolean exists(Clerk clerk){
		Assert.isNull(clerk);
		
		boolean result;
		
		result = clerkRepository.exists(clerk.getId());
		
		return result;
	}

	// Save solo debe usarse para guardar el objeto por primera vez
	public void save(Clerk clerk){
		Assert.isTrue(!this.exists(clerk));
		
		System.out.println("El método save en ClerkService no tiene en cuenta la concurrencia");
		clerkRepository.save(clerk);
	}

	//Other business methods -------------------------------------------------

	public Clerk findByOrder(Order order){
		Assert.notNull(order);
		
		Clerk result;
		
		result = clerkRepository.findByOrderId(order.getId());
		
		return result;
	}
	
	
	public Collection<Clerk> findClerkServerMoreOrders(){
		Collection<Clerk> result;
		
		result = clerkRepository.findClerkServerMoreOrders();
		
		return result;
	}
	
	public Collection<Clerk> findClerkServerLessOrders(){
		Collection<Clerk> result;
		
		result = clerkRepository.findClerkServerLessOrders();
		
		return result;
	}
	


}
