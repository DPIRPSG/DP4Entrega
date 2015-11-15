package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Category;
import domain.Item;

import utilities.AbstractTest;

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
	private CategoryService categoryService;
	
	// Test ---------------------------------------
	
	// Requisito 10.2
	@Test
	public void testFindAllByCategory1(){
		System.out.println("ItemServiceTest - testFindAllByCategory1 - StartPoint");
		
		Collection<Item> all;
		Category category;
		
		category = categoryService.findAll().iterator().next();
		all = itemService.findAllByCategory(category);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindAllByCategory1 - FinishPoint");
	}
	
	// Requisito 10.3
	@Test
	public void testFindByKeyword1(){
		System.out.println("ItemServiceTest - testFindByKeyword1 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "B-H6";
		all = itemService.findByKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword1 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword2(){
		System.out.println("ItemServiceTest - testFindByKeyword2 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "cer";
		all = itemService.findByKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword2 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword3(){
		System.out.println("ItemServiceTest - testFindByKeyword3 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		singleKeyword = "jor";
		all = itemService.findByKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		System.out.println("ItemServiceTest - testFindByKeyword3 - FinishPoint");
	}
	
	// Requisito 11.1
	@Test
	public void testFindAllByCategory2(){
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
		System.out.println("ItemServiceTest - testFindByKeyword4 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("consumer1");
		singleKeyword = "B-H6";
		all = itemService.findByKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);
		
		System.out.println("ItemServiceTest - testFindByKeyword4 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword5(){
		System.out.println("ItemServiceTest - testFindByKeyword5 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("consumer1");
		singleKeyword = "cer";
		all = itemService.findByKeyword(singleKeyword);
		
		for(Item i:all){
			System.out.println(i.getName());
		}
		
		authenticate(null);

		System.out.println("ItemServiceTest - testFindByKeyword5 - FinishPoint");
	}
	
	@Test
	public void testFindByKeyword6(){
		System.out.println("ItemServiceTest - testFindByKeyword6 - StartPoint");
		
		Collection<Item> all;
		String singleKeyword;
		
		authenticate("consumer1");
		singleKeyword = "jor";
		all = itemService.findByKeyword(singleKeyword);
		
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
		all = itemService.findByKeyword(singleKeyword);
		
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
		all = itemService.findByKeyword(singleKeyword);
		
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
		all = itemService.findByKeyword(singleKeyword);
		
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
		
		authenticate("admin");
		
		item = itemService.create();
		System.out.println("El item ya ha sido Creado, pero no debe estar Guardado en la BBDD ¿Está guardado?:\n" + itemService.findOne(item.getId()));
		itemService.save(item);
	
		authenticate(null);
		
		System.out.println("El item ya ha sido Creado y Guardado(persistido en la BBDD), ¿Es así?:\n" + itemService.findOne(item.getId()));
		
		System.out.println("ItemServiceTest - testCreate1 - FinishPoint");
	}
	
	@Test
	public void testUpdate1(){
		System.out.println("ItemServiceTest - testUpdate1 - StartPoint");
		
		Item item;
		int itemId;
		
		authenticate("admin");
		
		itemId = 54;
		System.out.println("Pretendemos modificar el item con el id 54, ¿Existe?, ¿Cuál es?:\n" + itemService.findOne(itemId));
		item = itemService.findOne(itemId);
		item.setName("Colonia alternativa");
		System.out.println("Se ha modificado el item SIN darle a Save, no debe estar persistido en la BBDD, ¿Es así?:\n" + itemService.findOne(itemId));
		itemService.save(item);
		System.out.println("Ya se ha pulsado en Save, el item debe estar persistido en la BBDD con las modificaciones, ¿Es así?:\n" + itemService.findOne(itemId));
	
		authenticate(null);
		
		System.out.println("ItemServiceTest - testUpdate1 - FinishPoint");
	}
	
	@Test
	public void testDelete1(){
		System.out.println("ItemServiceTest - testDelete1 - StartPoint");
		
		Item item;
		int itemId;
		
		authenticate("admin");
		
		itemId = 54;
		System.out.println("Pretendemos eliminar el item con el id 54, ¿Existe?, ¿Tiene la propiedad deleted = false?:\n" + itemService.findOne(itemId));
		item = itemService.findOne(itemId);
		itemService.delete(item);
		System.out.println("Ya se ha pulsado en Delete, el item debe estar en la BBDD con la propiedad deleted = true, ¿Es así?:\n" + itemService.findOne(itemId));
	
		authenticate(null);
		
		System.out.println("ItemServiceTest - testDelete1 - FinishPoint");
	}
}
