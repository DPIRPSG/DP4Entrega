package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

import repositories.ActorRepository;
import security.Authority;
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

	/**
	 * Comprueba si el usuario que está ejecutando tiene la AuthoritySolicitada
	 * @return boolean -> false si no es consumer
	 * @param authority [ADMIN, CONSUMER o CLERK]
	 */
	public boolean checkAuthority(String authority){
		boolean result;
		Actor actor;
		Collection<Authority> authorities;
		
		actor = this.findByPrincipal();
		authorities = actor.getUserAccount().getAuthorities();
		result = false;
		
		for (Authority a : authorities) {
			if(a.getAuthority().equals(authority.toUpperCase())){
				result = true;
				break;
			}
		}
		
		return result;
	}
}
