package services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Category;
import domain.Comment;
import domain.Item;
import domain.Storage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ItemServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;
	
	// Test ---------------------------------------
	
	// Requisito 10.2
	@Test
	public void testFindAllByCategory1(){
		System.out.println("Requisito 10.2 - List the catalogue of items grouped by their categories.");
		System.out.println("ItemServiceTest - testFindAllByCategory1 - StartPoint");

		Collection<Item> all;
		Category category;
		
		all = null;
		category = null;
		
		category = categoryService.findAll().iterator().next();
		//Peta aqu� con algo de las Collections. Parece que el error nos lleva hasta el repo
		all = itemService.findAllByCategory(category);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindAllByCategory1 - FinishPoint");
	}
	
	// Requisito 10.3
	@Test
	public void testFindByKeyword1(){
		System.out.println("Requisito 10.3 - Search for an item using a single keyword that must appear verbatim in its SKU, its name, or its description.");
		System.out.println("ItemServiceTest - testFindByKeyword1 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "B-H6";
		
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword1 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword2(){
		System.out.println("Requisito 10.3 - Search for an item using a single keyword that must appear verbatim in its SKU, its name, or its description.");
		System.out.println("ItemServiceTest - testFindByKeyword2 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "cer";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword2 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword3(){
		System.out.println("Requisito 10.3 - Search for an item using a single keyword that must appear verbatim in its SKU, its name, or its description.");
		System.out.println("ItemServiceTest - testFindByKeyword3 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "jor";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword3 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword10(){
		System.out.println("Requisito 10.3 - Search for an item using a single keyword that must appear verbatim in its SKU, its name, or its description.");
		System.out.println("ItemServiceTest - testFindByKeyword3 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "manolo";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		System.out.println(all);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword3 - FinishPoint");
	}
	
	// Requisito 11.1
	@Test
	public void testFindAllByCategory2(){
		System.out.println("Requisito 11.1 - Do the same as a user who is not authenticated, except for registering to the system.");
		System.out.println("ItemServiceTest - testFindAllByCategory2 - StartPoint");
		
		Collection<Item> all;
		Category category;
		
		authenticate("consumer1");
		
		category = categoryService.findAll().iterator().next();
		all = itemService.findAllByCategory(category);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindAllByCategory2 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword4(){
		System.out.println("Requisito 11.1 - Do the same as a user who is not authenticated, except for registering to the system.");
		System.out.println("ItemServiceTest - testFindByKeyword4 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("consumer1");
		
		singleKeyword = "B-H6";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindByKeyword4 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword5(){
		System.out.println("Requisito 11.1 - Do the same as a user who is not authenticated, except for registering to the system.");
		System.out.println("ItemServiceTest - testFindByKeyword5 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("consumer1");
		
		singleKeyword = "cer";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);

		System.out.println("ItemServiceTest - testFindByKeyword5 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword6(){
		System.out.println("Requisito 11.1 - Do the same as a user who is not authenticated, except for registering to the system.");
		System.out.println("ItemServiceTest - testFindByKeyword6 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("consumer1");
		singleKeyword = "jor";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindByKeyword6 - FinishPoint");
	}
	
	// Requisito 12.1
	@Test
	public void testFindAllByCategory3(){
		System.out.println("ItemServiceTest - testFindAllByCategory3 - StartPoint");
		
		Collection<Item> all;
		Category category;
		
		authenticate("admin");
		category = categoryService.findAll().iterator().next();
		all = itemService.findAllByCategory(category);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindAllByCategory3 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword7(){
		System.out.println("ItemServiceTest - testFindByKeyword7 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("admin");
		singleKeyword = "B-H6";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindByKeyword7 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword8(){
		System.out.println("ItemServiceTest - testFindByKeyword8 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("admin");
		singleKeyword = "cer";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);

		System.out.println("ItemServiceTest - testFindByKeyword8 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword9(){
		System.out.println("ItemServiceTest - testFindByKeyword9 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("admin");
		singleKeyword = "jor";
		all = itemService.findBySingleKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindByKeyword9 - FinishPoint");
	}
	
	// Requisito 12.2
	@Test
	public void testCreate1(){
		System.out.println("ItemServiceTest - testCreate1 - StartPoint");
		
		Item item;
		Item itemCreated;
		Collection<Item> all;
		Comment comment;
		Collection<Comment> comments;
		Category category;
		Set<Storage> storages;
		
		authenticate("admin");
		
		System.out.println("Vamos a crear el Item \"Tablet 7 pulgadas\"");
		item = itemService.create();
		item.setSku("AA-1234");
		item.setName("Tablet 7 pulgadas");
		item.setDescription("La tablet m�s vers�til.");
		item.setPrice(199.99);
		comment = commentService.createByItem(item);
		comments = new HashSet<>();
		comments.add(comment);
		item.setComments(comments);
		category = categoryService.findAll().iterator().next();
		item.setCategory(category);
		storages = Collections.emptySet();
		item.setStorages(storages);
		itemService.save(item);
		System.out.println("El item ya ha sido Creado y debe estar Guardado en la BBDD �Es as�?:");
		all = itemService.findAll();
		for (Item i: all){
			if(i.getSku()==item.getSku()){
				itemCreated = i;
				System.out.println("Nombre: " + itemCreated.getName());
				System.out.println("Descripci�n: " + itemCreated.getDescription());
				System.out.println("Id: " + itemCreated.getId());
			}
		}
	
		authenticate(null);
		
		System.out.println("ItemServiceTest - testCreate1 - FinishPoint");
	}
	
	@Test
	public void testUpdate1(){
		System.out.println("ItemServiceTest - testUpdate1 - StartPoint");
		
		Item item;
		Item itemModified;
		Item itemNotModified;
		Collection<Item> all;
		String sku;
		
		authenticate("admin");
		
		sku = "CJ-C8JW";
		System.out.println("Pretendemos modificar el item con el sku CJ-C8JW, �Existe?, �Cu�l es?:");
		all = itemService.findAll();
		item = null;
		for (Item i: all){
			if(i.getSku()==sku){
				item = i;
				System.out.println("Nombre: " + item.getName());
				System.out.println("Descripci�n: " + item.getDescription());
				System.out.println("Id: " + item.getSku() + "\n");
			}else{
				System.out.println("No hay ning�n item con el sku: " + sku);
			}
		}
		item.setName("TV Plasma");
		System.out.println("Se ha modificado el item SIN darle a Save, no debe estar persistido en la BBDD, �Es as�?:");
		all = itemService.findAll();
		itemNotModified = null;
		for (Item i: all){
			if(i.getSku().equals(sku)){
				itemNotModified = i;
				System.out.println("Nombre: " + itemNotModified.getName());
				System.out.println("Descripci�n: " + itemNotModified.getDescription());
				System.out.println("Id: " + itemNotModified.getId() + "\n");
			}
		}
		//itemService.save(itemToModify);
		System.out.println("Ya se ha pulsado en Save, el item debe estar persistido en la BBDD con las modificaciones, �Es as�?:");
//		itemModified = itemService.findOne(itemId);
//		System.out.println("Nombre: " + itemModified.getName());
//		System.out.println("Descripci�n: " + itemModified.getDescription());
//		System.out.println("Id: " + itemModified.getId());
		all = itemService.findAll();
		itemModified = null;
		for (Item i: all){
			if(i.getSku()==sku){
				itemModified = i;
				System.out.println("Nombre: " + itemModified.getName());
				System.out.println("Descripci�n: " + itemModified.getDescription());
				System.out.println("Id: " + itemModified.getId());
			}
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testUpdate1 - FinishPoint");
	}
	
	@Test
	public void testDelete1(){
		System.out.println("ItemServiceTest - testDelete1 - StartPoint");
		
		Item item;
		Item itemDeleted;
		int itemId;
		
		authenticate("admin");
		
		itemId = 54;
		System.out.println("Pretendemos eliminar el item con el id 54, �Existe?, �Tiene la propiedad deleted = false?:");
		item = itemService.findOne(itemId);
		System.out.println("Nombre: " + item.getName());
		System.out.println("Id: " + item.getId());
		System.out.println("deleted: " + item.getDeleted() + "\n");
		itemService.delete(item);
		System.out.println("Ya se ha pulsado en Delete, el item debe estar en la BBDD con la propiedad deleted = true, �Es as�?:");
		itemDeleted = itemService.findOne(itemId);
		System.out.println("Nombre: " + itemDeleted.getName());
		System.out.println("Id: " + itemDeleted.getId());
		System.out.println("deleted: " + itemDeleted.getDeleted());
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testDelete1 - FinishPoint");
	}
	
	@Test
	public void testFindItemBestSelling1(){
		System.out.println("Requisito 12.7.3 - The best-selling item/s in the inventory.");
		System.out.println("ConsumerServiceTest - testFindConsumerSpentMoreMoney1 - StartPoint");
		
		Collection<Item> all;
		
		authenticate("admin");
		
		all = itemService.findItemBestSelling();
		
		System.out.println("The best-selling item/s in the inventory.");
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testFindConsumerSpentMoreMoney1 - FinishPoint");
	}
	
	@Test
	public void testFindItemWorstSelling1(){
		System.out.println("Requisito 12.7.4 - The worst-selling item/s in the inventory.");
		System.out.println("ConsumerServiceTest - testFindItemWorstSelling1 - StartPoint");
		
		Collection<Item> all;
		
		authenticate("admin");
		
		all = itemService.findItemWorstSelling();
		
		System.out.println("The worst-selling item/s in the inventory.");
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testFindItemWorstSelling1 - FinishPoint");
	}
}
