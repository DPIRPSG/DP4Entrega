package services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Item;
import domain.Storage;
import domain.WareHouse;

import repositories.StorageRepository;

@Service
@Transactional
public class StorageService {
 	//Managed repository -----------------------------------------------------

	private StorageRepository storageRepository;
	
	//Supporting services ----------------------------------------------------
	
	private WareHouseService wareHouseService;
	private ItemService itemService;

	//Constructors -----------------------------------------------------------

	public StorageService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public boolean exists(Storage storage){
		Assert.isNull(storage);
		
		boolean result;
		
		result = storageRepository.exists(storage.getId());
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	public Storage findByWareHouseAndItem(WareHouse wareHouse, Item item){
		Assert.isTrue(wareHouseService.exists(wareHouse));
		Assert.isTrue(itemService.exists(item));
		
		Storage result;
		
		result = storageRepository.findByWareHouseIdAndItemId(wareHouse.getId(), item.getId());
		
		return result;
	}
 
}
