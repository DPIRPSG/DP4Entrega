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
	
	public boolean exists(ExchangeRate exchangeRate){
		Assert.isNull(exchangeRate);
		
		boolean result;
		
		result = exchangeRateRepository.exists(exchangeRate.getId());
		
		return result;
	}
	
	public ExchangeRate save(ExchangeRate input){
		Assert.isTrue(this.exists(input));
		
		ExchangeRate result;
		
		result = exchangeRateRepository.save(input);
		
		return result;
	}
	
	public void delete(ExchangeRate exchangeRate){
		Assert.isTrue(this.exists(exchangeRate));
		
		exchangeRateRepository.delete(exchangeRate.getId());
	}
	
	public Collection<ExchangeRate> findAll(){
		Collection<ExchangeRate> result;
		
		result = exchangeRateRepository.findAll();
		
		return result;		
	}
	
	// Other business methods -------------------------------------------------
		
	
}
