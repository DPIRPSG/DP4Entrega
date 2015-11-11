package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

import repositories.ActorRepository;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;
	
	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService userAccountService;
	
	// Constructors -----------------------------------------------------------
	
	public ActorService(){
		super();
		System.out.println("La clase ActorService no está terminada, faltarían los mensajes y todo eso");
	}
	// Simple CRUD methods ----------------------------------------------------

	public Collection<Actor> findAll(){
		Collection<Actor> result;
		
		result = actorRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	
	public Actor findOne(int actorId){
		Assert.isTrue(actorId != 0);
		
		Actor result;
		
		result = actorRepository.findOne(actorId);
		Assert.notNull(result);
		
		return result;
	}
	
	public void save(Actor actor){
		Assert.notNull(actor);
		
		actorRepository.save(actor);
	}
	
	public boolean exists(Actor actor){
		Assert.notNull(actor);
		
		boolean result;
		
		result = actorRepository.exists(actor.getId());
		
		return result;
	}
	
	// Other business methods -------------------------------------------------
	
	public UserAccount findUserAccount(Actor actor){
		Assert.notNull(actor);
		
		UserAccount result;
		
		result = userAccountService.findByActor(actor);
		
		return result;
	}
	
}
