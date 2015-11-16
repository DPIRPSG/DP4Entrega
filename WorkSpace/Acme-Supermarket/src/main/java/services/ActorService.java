package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;


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

	/**
	 *  Devuelve el actor que está realizando la operación
	 */
	//req: 24.1, 24.2
	public Actor findByPrincipal(){
		Actor result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = actorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}
}
