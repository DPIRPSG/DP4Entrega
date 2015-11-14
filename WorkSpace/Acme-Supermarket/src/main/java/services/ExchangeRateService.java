package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ExchangeRate;

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
	}
	// Simple CRUD methods ----------------------------------------------------
	
	public ExchangeRate create(){
		ExchangeRate result;
		
		result = new ExchangeRate();
		
		return result;
	}
	
	public void save(ExchangeRate exchangeRate){
		Assert.notNull(exchangeRate);
		
		exchangeRateRepository.save(exchangeRate);
	}
	
	public void delete(ExchangeRate exchangeRate){
		Assert.notNull(exchangeRate);
		
		exchangeRateRepository.delete(exchangeRate.getId());
	}
	
	public Collection<ExchangeRate> findAll(){
		Collection<ExchangeRate> result;
		
		result = exchangeRateRepository.findAll();
		
		return result;		
	}
	
	// Other business methods -------------------------------------------------
		
	
}
