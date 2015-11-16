package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private StorageRepository storageRepository;
	
	//Supporting services ----------------------------------------------------
	
	//Constructors -----------------------------------------------------------

	public StorageService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	/**
	 * USAR EN WareHouse. Devuelve WareHouse preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */
	//req: 17.5
	private Storage create(){
		Storage result;
		
		result = new Storage();
		
		return result;
	}
	
	/**
	 * Guarda un storage creado o modificado
	 */
	//req: 17.5
	private void save(Storage storage){
		Assert.notNull(storage);
		
		if(storage.getUnits() <= 0){
			this.delete(storage);
		}else{
			storageRepository.save(storage);
		}
	}
	
	/**
	 * Elimina un storage
	 */
	//req: 17.5
	private void delete(Storage storage){
		Assert.notNull(storage);
		Assert.isTrue(storage.getId() != 0);
		
		storageRepository.delete(storage);
	}

	//Other business methods -------------------------------------------------
	
	/**
	 * Dado un wareHouse y un item, busca el storage
	 */
	//req: 17.5
	private Storage findByWareHouseAndItem(WareHouse wareHouse, Item item){
		Assert.notNull(wareHouse);
		Assert.notNull(item);
		
		Storage result;
		
		result = storageRepository.findByWareHouseIdAndItemId(wareHouse.getId(), item.getId());
		
		return result;
	}
	
	/**
	 * USAR EN wareHouseService. Dado un wareHouse y un item, actualiza la cantidad
	 */
	//req: 17.5
	public void updateQuantityByWareHouseAndItem(WareHouse wareHouse, Item item, int units){
		Assert.notNull(wareHouse);
		Assert.isTrue(wareHouse.getId() != 0);
		Assert.notNull(item);
		Assert.isTrue(item.getId() != 0);
		Assert.isTrue(units >= 0);
		
		Storage storage;
		
		storage = this.findByWareHouseAndItem(wareHouse, item);
		
		if(storage == null){
			storage = this.create();
			storage.setWareHouse(wareHouse);
			storage.setItem(item);
		}
		
		storage.setUnits(units);
		
		this.save(storage);
	}
	
	/**
	 * Cantidad de Item en un warehouse
	 */
	//ref: 18.4
	public int quantityByWareHouseAndItem(WareHouse wareHouse, Item item){
		int result;
		Storage storage;
		
		storage = this.findByWareHouseAndItem(wareHouse, item);
		result = storage.getUnits();
		
		return result;
	}
 
}
