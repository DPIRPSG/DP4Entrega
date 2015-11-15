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
	@Autowired
	private CategoryService categoryService;
	
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
		//Peta aquí con algo de las Collections. Parece que el error nos lleva hasta el repo
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
}
