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

		//Copiado de Acme-Supermarket
	/*public Collection<Actor> findAll(){
		Collection<Actor> result;
		
		result = actorRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}*/
	
		//Copiado de Acme-Supermarket
	/*public Actor findOne(int actorId){
		Assert.isTrue(actorId != 0);
		
		Actor result;
		
		result = actorRepository.findOne(actorId);
		Assert.notNull(result);
		
		return result;
	}*/
	
		//Copiado de Acme-Supermarket
	/*public void save(Actor actor){
		Assert.notNull(actor);
		
		actorRepository.save(actor);
	}*/
	
		//Copiado de Acme-Supermarket
	/*public void delete(Actor actor){
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(actorRepository.exists(actor.getId()));
		
		actorRepository.delete(actor);
	}*/
	
	// Other business methods -------------------------------------------------
	
		//Copiado de Acme-Supermarket
	/*public UserAccount findUserAccount(Actor actor){
		Assert.notNull(actor);
		
		UserAccount result;
		
		result = userAccountService.findByActor(actor);
		
		return result;
	}*/
	
}
