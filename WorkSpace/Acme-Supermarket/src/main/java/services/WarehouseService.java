package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Item;
import domain.Storage;
import domain.WareHouse;

import repositories.ItemRepository;
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
	
	public boolean exists(WareHouse wareHouse){
		Assert.isNull(wareHouse);
		
		boolean result;
		
		result = wareHouseRepository.exists(wareHouse.getId());
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	public void UpdateItemQuantity(WareHouse wareHouse, Item item, int quantity){
		Assert.isTrue(this.exists(wareHouse));
		Assert.isTrue(itemService.exists(item));
		
		Storage storage;
		
		storage = storageService.findByWareHouseAndItem(wareHouse, item);
		
		// Si devuelve null se debe crear
		// si la cantidad entrante es 0 se debe borrar
		System.out.println("El método UpdateItemQuantity en WareHouseService no está finalizado");
	}
	
	public Collection<WareHouse> findAllByItem(Item item){
		Assert.isTrue(itemService.exists(item));
		
		Collection<WareHouse> result;
		
		result = wareHouseRepository.findAllByItemId(item.getId());
		
		return result;
	}
 
}
