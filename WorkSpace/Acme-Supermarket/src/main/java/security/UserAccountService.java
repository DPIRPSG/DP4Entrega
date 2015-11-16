package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	
	public UserAccountService(){
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	
	// Other business methods -------------------------------------------------


}
