package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;


@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;
	
	// Supporting services ----------------------------------------------------

	/*@Autowired
	private UserAccountService userAccountService;*/
	
	// Constructors -----------------------------------------------------------
	
	public ActorService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

	
	// Other business methods -------------------------------------------------

	
}
