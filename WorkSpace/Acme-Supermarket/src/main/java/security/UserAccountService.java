package security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------
	
	/*@Autowired
	private UserAccountRepository userAccountRepository;*/
	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	
	public UserAccountService(){
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	private UserAccount create(String authority){
		UserAccount result;
		Authority au;
		Collection<Authority> authorities = new ArrayList<Authority>();
		
		au = new Authority();
		
		au.setAuthority(authority);
		
		authorities.add(au);
		
		result = new UserAccount();
		
		result.setAuthorities(authorities);
		
		return result;
	}
	// Other business methods -------------------------------------------------
	public UserAccount createComplete(String username, String Password, String authority){
		UserAccount result;
		
		result = this.create(authority);
		
		
		result.setUsername(username);
		result.setPassword(Password);
		
		return result;
	}

}
