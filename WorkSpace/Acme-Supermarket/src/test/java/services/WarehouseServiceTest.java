package services;

import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Storage;
import domain.WareHouse;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class WarehouseServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private WareHouseService warehouseService;
	
	// Test ---------------------------------------
	@Test
	public void testFindAll1(){
		System.out.println("Requisito 17.2 - List the warehouses and navigate to the items that they store.");
		System.out.println("WarehouseServiceTest - testFindAll1 - StartPoint");
		
		Collection<WareHouse> all;
		
		authenticate("admin");
		
		all = warehouseService.findAll();
		
		System.out.println("Lista de Warehouses");
		for(WareHouse w:all){
			System.out.println(w.getName());
		}
		
		System.out.println("Lista de Items de Warehouse");
		for(WareHouse w2:all){
			System.out.println("Items de: " + w2.getName());
			for(Storage s: w2.getStorages()){
				System.out.println(s.getItem().getName());
			}
		}
		
		authenticate(null);
		System.out.println("WarehouseServiceTest - testFindAll1 - FinishPoint");
	}
	
	@Test
	public void testCreate1(){
		System.out.println("Requisito 17.3 - Register a new warehouse to the system.");
		System.out.println("WarehouseServiceTest - testCreate1 - StartPoint");
		
		WareHouse result;
		Collection<WareHouse> all;
		Collection<Storage> storages;
		
		authenticate("admin");
		
		storages = Collections.emptyList();
		all = warehouseService.findAll();
		System.out.println("Lista de Warehouses antes de la creación de otro");
		for(WareHouse w:all){
			System.out.println(w.getName());
		}
		
		result = warehouseService.create();
		result.setName("Nombre creado");
		result.setAddress("Adress creada");
		result.setStorages(storages);
		warehouseService.save(result);
		
		all = warehouseService.findAll();
		System.out.println("Lista de Warehouses después de la creación de otro");
		for(WareHouse w2:all){
			System.out.println(w2.getName());
		}
		
		authenticate(null);
		System.out.println("WarehouseServiceTest - testCreate1 - FinishPoint");
	}
	
	@Test
	public void testModify1(){
		System.out.println("Requisito 17.4 - Modify, update, or delete a warehouse.");
		System.out.println("WarehouseServiceTest - testModify1 - StartPoint");
		
		WareHouse warehouse;
		
		authenticate("admin");
		
		warehouse = warehouseService.findAll().iterator().next();
		System.out.println("Warehouse original con la propiedad antes de modificar");
		System.out.println(warehouse.getName());
		
		warehouse.setName("Nombre modificado");
		warehouseService.save(warehouse);
		warehouse = warehouseService.findAll().iterator().next();
		
		System.out.println("Warehouse original con la propiedad después de modificarla");
		System.out.println(warehouse.getName());
		
		authenticate(null);
		System.out.println("WarehouseServiceTest - testModify1 - FinishPoint");
	}
	
	@Test
	public void testDelete1(){
		System.out.println("Requisito 17.4 - Modify, update, or delete a warehouse.");
		System.out.println("WarehouseServiceTest - testDelete1 - StartPoint");
		
		WareHouse warehouse;
		Collection<WareHouse> all;
		
		authenticate("admin");
		
		warehouse = null;
		all = warehouseService.findAll();
		for(WareHouse w:all){
			if(w.getStorages().isEmpty()){
				//Aquí cogemos el warehouse adecuado.
				warehouse = w;
			}
		}
		
		System.out.println("Warehouse antes de borrar alguno");
		for(WareHouse w2:all){
			System.out.println(w2.getName());
		}
		
		warehouseService.delete(warehouse);
		all = warehouseService.findAll();
		
		System.out.println("Warehouse después de borra alguno");
		for(WareHouse w3:all){
			System.out.println(w3.getName());
		}
		
		authenticate(null);
		
		System.out.println("WarehouseServiceTest - testDelete1 - FinishPoint");
	}

	@Test
	public void testQuantityItemInWarehouse1(){
		System.out.println("WarehouseServiceTest - testQuantityItemInWarehouse1 - StartPoint");
		
		WareHouse warehouse;
		Item item;
		int oldQuantity;
		int setQuantity;
		int newQuantity;
		
		authenticate("admin");
		
		System.out.println("Vamos a coger un item que ya exista en un warehouse existente:");
		warehouse = warehouseService.findAll().iterator().next();
		item = itemService.findAllByWareHouse(warehouse).iterator().next();
		oldQuantity = storageService.quantityByWareHouseAndItem(warehouse, item);
		System.out.println("Warehouse: " + warehouse.getName());
		System.out.println("Item: " + item.getName());
		System.out.println("ID del Item: " + item.getId());
		System.out.println("Cantidad almacenada: " + oldQuantity);
		System.out.println("Aumentemos en 5 la cantidad de items almacenados, ¿Surge efecto?:");
		setQuantity = oldQuantity+5;
		warehouseService.changeItemQuantity(warehouse, item, setQuantity);
		newQuantity = storageService.quantityByWareHouseAndItem(warehouse, item);
		System.out.println("Warehouse: " + warehouse.getName());
		System.out.println("Item: " + item.getName());
		System.out.println("ID del Item: " + item.getId());
		System.out.println("Cantidad almacenada: " + newQuantity);
		
		authenticate(null);
		
		System.out.println("WarehouseServiceTest - testQuantityItemInWarehouse1 - FinishPoint");
	}
}
