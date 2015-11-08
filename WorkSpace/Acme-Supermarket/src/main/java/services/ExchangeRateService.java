package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExchangeRateRepository;


@Service
@Transactional
public class ExchangeRateService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------

	public ExchangeRateService(){
		super();
		System.out.println("La clase ExchangeRateService no está acabada");
	}
	// Simple CRUD methods ----------------------------------------------------
	
	//findAll
	
	
	// Other business methods -------------------------------------------------
		
	
}
