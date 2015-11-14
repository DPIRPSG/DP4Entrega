package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Item;
import domain.WareHouse;

import repositories.WareHouseRepository;

@Service
@Transactional 
public class WareHouseService {
 	//Managed repository -----------------------------------------------------

	private WareHouseRepository wareHouseRepository;
	
	//Supporting services ----------------------------------------------------

	private ItemService itemService;
	private StorageService storageService;
	
	//Constructors -----------------------------------------------------------

	public WareHouseService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public Collection<WareHouse> findAll(){
		Collection<WareHouse> result;
		
		result = wareHouseRepository.findAll();
		
		return result;
	}
	
	public WareHouse create(){
		WareHouse result;
		
		result = new WareHouse();
		
		return result;
	}
	
	public void save(WareHouse wareHouse){
		Assert.notNull(wareHouse);
		
		wareHouseRepository.save(wareHouse);
	}
	
	public void delete(WareHouse wareHouse){
		Assert.notNull(wareHouse);
		
		Collection<Item> items;
		
		items = itemService.findAllByWareHouse(wareHouse);
		
		if (items.isEmpty()){
			wareHouseRepository.delete(wareHouse);
		}else{
			// No se puede borrar ya que todavía contiene algún item
		}
	}
	
	//Other business methods -------------------------------------------------
	
	public void changeItemQuantity(WareHouse wareHouse, Item item, int quantity){
		storageService.updateQuantityByWareHouseAndItem(wareHouse, item, quantity);
	}
	
	public void removeItemQuantity(WareHouse wareHouse, Item item, int quantityToEliminate){
		int actualQuantity;
		Integer finalQuantity;
		
		actualQuantity = storageService.quantityByWareHouseAndItem(wareHouse, item);
		finalQuantity = actualQuantity - quantityToEliminate;
		
		// Intenta eliminar más de los Items que hay
		Assert.isTrue(finalQuantity >= 0);
		
		storageService.updateQuantityByWareHouseAndItem(wareHouse, item, finalQuantity);
	}
	
	public Collection<WareHouse> findAllByItem(Item item){
		Assert.isTrue(itemService.exists(item));
		
		Collection<WareHouse> result;
		
		result = wareHouseRepository.findAllByItemId(item.getId());
		
		return result;
	}
}
