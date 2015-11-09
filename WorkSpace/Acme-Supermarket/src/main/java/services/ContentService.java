package services;

import java.util.Collection;

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

	private ContentRepository contentRepository;
	
	//Supporting services ----------------------------------------------------

	private ShoppingCartService shoppingCartService;
	
	//Constructors -----------------------------------------------------------

	public ContentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public void save(Content content){
		Assert.isNull(content);
		
		contentRepository.save(content);
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
