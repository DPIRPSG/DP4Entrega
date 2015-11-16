package services;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.Storage;
import domain.WareHouse;

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
	@Autowired
	private ItemService itemService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private OrderService orderService;
	
	
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
		System.out.println("Lista de Warehouses antes de la creaci�n de otro");
		for(WareHouse w:all){
			System.out.println(w.getName());
		}
		
		result = warehouseService.create();
		result.setName("Nombre creado");
		result.setAddress("Adress creada");
		result.setStorages(storages);
		warehouseService.save(result);
		
		all = warehouseService.findAll();
		System.out.println("Lista de Warehouses despu�s de la creaci�n de otro");
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
		
		System.out.println("Warehouse original con la propiedad despu�s de modificarla");
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
				warehouse = w;
			}
		}
		
		System.out.println("Warehouse antes de borrar alguno");
		for(WareHouse w2:all){
			System.out.println(w2.getName());
		}
		
		warehouseService.delete(warehouse);
		all = warehouseService.findAll();
		
		System.out.println("Warehouse despu�s de borra alguno");
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
		System.out.println("Aumentemos en 5 la cantidad de items almacenados, �Surge efecto?:");
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
	
	@Test
	public void testRemoveAndServe1(){
		System.out.println("WarehouseServiceTest - testRemoveAndServe1 - StartPoint");
		
		Collection<WareHouse> warehouses;
		Iterator<WareHouse> warehousesIt;
		WareHouse warehouse1;
		WareHouse warehouse2;
		Item item1;
		Item item2;
		int itemQuantity1;
		int itemQuantity2;
		Order order;
		OrderItem orderItem;
		
		authenticate("clerk1");
		
		System.out.println("Cogemos un par de Warehouses y un Item que contengan ambos:");
		warehouses = warehouseService.findAll();
		warehousesIt = warehouses.iterator();
		warehouse1 = warehousesIt.next();
		System.out.println("Warehouse: " + warehouse1.getName());
		item1 = itemService.findAllByWareHouse(warehouse1).iterator().next();
		System.out.println("Item: " + item1.getName());
		itemQuantity1 = storageService.quantityByWareHouseAndItem(warehouse1, item1);
		System.out.println("Unidades disponibles: " + itemQuantity1);
		warehouse2 = warehousesIt.next();
		System.out.println("\nWarehouse: " + warehouse2.getName());
		item2 = itemService.findAllByWareHouse(warehouse2).iterator().next();
		System.out.println("Item: " + item2.getName());
		itemQuantity2 = storageService.quantityByWareHouseAndItem(warehouse2, item2);
		System.out.println("Unidades disponibles: " + itemQuantity2);
		
		System.out.println("\nQuitamos un par de unidades cada Warehouse");
		order = orderService.findAll().iterator().next();
		warehouseService.addItemToOrderItem(warehouse1, item1, 1, order);
		warehouseService.addItemToOrderItem(warehouse2, item2, 1, order);
		
		System.out.println("\nComprobamos ahora el n�mero de unidades de cada item en su correspondiente Warehouse:");
		warehouses = warehouseService.findAll();
		warehousesIt = warehouses.iterator();
		warehouse1 = warehousesIt.next();
		System.out.println("Warehouse: " + warehouse1.getName());
		item1 = itemService.findAllByWareHouse(warehouse1).iterator().next();
		System.out.println("Item: " + item1.getName());
		itemQuantity1 = storageService.quantityByWareHouseAndItem(warehouse1, item1);
		System.out.println("Unidades disponibles: " + itemQuantity1);
		warehouse2 = warehousesIt.next();
		System.out.println("\nWarehouse: " + warehouse2.getName());
		item2 = itemService.findAllByWareHouse(warehouse2).iterator().next();
		System.out.println("Item: " + item2.getName());
		itemQuantity2 = storageService.quantityByWareHouseAndItem(warehouse2, item2);
		System.out.println("Unidades disponibles: " + itemQuantity2);
		
		System.out.println("\nComprobamos ahora el n�mero de unidades servidas del orderItem:");
		orderItem = order.getOrderItems().iterator().next();
		System.out.println("OrderItem: " + orderItem.getName());
		System.out.println("Unidades servidas: " + orderItem.getUnitsServed());
		
		authenticate(null);
		
		System.out.println("WarehouseServiceTest - testRemoveAndServe1 - FinishPoint");
	}
}
