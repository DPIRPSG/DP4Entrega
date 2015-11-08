package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Clerk;

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

	//Other business methods -------------------------------------------------

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
