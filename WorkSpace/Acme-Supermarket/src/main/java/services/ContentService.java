package services;

import java.util.Collection;

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

	/*@Autowired
	private ShoppingCartService shoppingCartService;*/
	
	//Constructors -----------------------------------------------------------

	public ContentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public Content create(){
		Content result;
		
		result = new Content();
		result.setUnits(1);
		
		return result;
	}
	
	
	public void save(Content content){
		Assert.notNull(content);
	
		if(content.getUnits() == 0){
			this.delete(content);
		}else{
			contentRepository.save(content);
		}
	}
	
	public void delete(Content content){
		Assert.notNull(content);
		Assert.isTrue(content.getId() != 0);
		
		contentRepository.delete(content.getId());
	}
	
	//Other business methods -------------------------------------------------
 
	private Content findByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		
		Content result;
		
		result = contentRepository.findByShoppingCartIdAndItemId(shoppingCart.getId(), item.getId());
		
		return result;
	}
	
	public void emptyByShoppingCart(ShoppingCart shoppingCart){
		Collection<Content> contents;
		
		contents = this.findByShoppingCart(shoppingCart);
		
		for (Content content : contents) {
			this.delete(content);
		}
	}
	
	private Collection<Content> findByShoppingCart(ShoppingCart shoppingCart){
		Collection<Content> result;
		
		result = contentRepository.findByShoppingCartID(shoppingCart.getId());
		
		return result;
	}
	
	public int contentByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		int result;
		Content content;
		
		content = this.findByShoppingCartAndItem(shoppingCart, item);
		result = content.getUnits();
		
		return result;
	}
	
	public void updateQuantityByShoppingCartAndItem(ShoppingCart shoppingCart, Item item, int quantity){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		
		Content content;
		
		content = this.findByShoppingCartAndItem(shoppingCart, item);
		
		if(content == null){
			content = this.create();
			content.setShoppingCart(shoppingCart);
			content.setItem(item);
		}
		
		content.setUnits(quantity);
		
		this.save(content);
	}
	
	public void createByShoppingCartAndItem(ShoppingCart shoppingCart, Item item){
		this.updateQuantityByShoppingCartAndItem(shoppingCart, item, 1);
	}
	
	
}
