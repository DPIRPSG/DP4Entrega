package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Content;
import domain.Item;
import domain.ShoppingCart;

import repositories.ContentRepository;

@Service
@Transactional
public class ContentService {

 	//Managed repository -----------------------------------------------------

	@Autowired
	private ContentRepository contentRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//Constructors -----------------------------------------------------------

	public ContentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public Content create(){
		Content result;
		
		result = new Content();
		System.out.println("El método create dentro de ContentService no está implementado");
		
		return result;
	}
	
	
	public Content save(Content content){
		Assert.isTrue(!this.exists(content));
		
		Content result;
		
		System.out.println("El método save en ContentService no comprueba la concurrencia");
		result = contentRepository.save(content);
		
		return result;
	}
	
	public Content update(Content content){
		Assert.isTrue(this.exists(content));
		
		Content result;
		
		System.out.println("El método update en ContentService no comprueba la concurrencia");
		result = contentRepository.save(content);
		
		return result;
	}
	
	public boolean exists(Content content){
		Assert.isNull(content);
		
		boolean result;
		
		result = contentRepository.exists(content.getId());
		
		return result;
	}
	//Other business methods -------------------------------------------------
 
	public Content findByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		Assert.isNull(shoppingCart);
		Assert.isTrue(shoppingCartService.exists(shoppingCart));
		
		Content result;
		
		result = contentRepository.findByShoppingCartIdAndItemId(shoppingCart.getId(), item.getId());
		
		return result;
	}
}
