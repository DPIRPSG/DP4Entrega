package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Consumer;
import domain.Content;
import domain.Item;
import domain.Order;
import domain.ShoppingCart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ShoppingCartServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private ShoppingCartService shoppingCartService;
	private ItemService itemService;
	
	// Test ---------------------------------------
	// Requisito 11.3
	@Test
	public void testAddItem1(){
		System.out.println("ShoppingCartServiceTest - testAddItem1 - StartPoint");
		ShoppingCart shoppingCart;
		Item item;
		Boolean result;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		item = itemService.findAll().iterator().next();
		
		System.out.println("Lista de Items de ShoppingCart antes de añadir el nuevo item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		shoppingCart.addItem(shoppingCart, item);
		
		System.out.println("Lista de Items de ShoppingCart después de añadir el nuevo item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		System.out.println("ShoppingCartServiceTest - testAddItem1 - FinishPoint");

	}
	
	// Requisito 11.4
	@Test
	public void testChangeItemQuantity1(){
		System.out.println("ShoppingCartServiceTest - testChangeItemQuantity1 - StartPoint");
		ShoppingCart shoppingCart;
		Item item;
		Boolean result;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		item = itemService.findAll().iterator().next();
		
		System.out.println("Lista de Items de ShoppingCart antes de cambiar el número de unidades de un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		shoppingCart.changeItemQuantity(shoppingCart, item, 17);
		
		System.out.println("Lista de Items de ShoppingCart después de cambiar el número de unidades de un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		System.out.println("ShoppingCartServiceTest - testChangeItemQuantity1 - FinishPoint");

	}
	
	// Requisito 11.5
	@Test
	public void testChangeItemQuantity2(){
		System.out.println("ShoppingCartServiceTest - testChangeItemQuantity1 - StartPoint");
		ShoppingCart shoppingCart;
		Item item;
		Boolean result;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		item = itemService.findAll().iterator().next();
		
		System.out.println("Lista de Items de ShoppingCart antes de borrar un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		shoppingCart.changeItemQuantity(shoppingCart, item, 17);
		
		System.out.println("Lista de Items de ShoppingCart después de borrar un item:");
		for(Content c: shoppingCart.getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		System.out.println("ShoppingCartServiceTest - testChangeItemQuantity1 - FinishPoint");

	}
	
	// Requisito 11.6
	@Test
	public void testAddComment1(){
		System.out.println("ShoppingCartServiceTest - testAddComment1 - StartPoint");
		ShoppingCart shoppingCart;
		String comment;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		comment = "comentario de los test";
		
		System.out.println("Comentarios del ShoppingCart antes de añadir un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		shoppingCart.addComment(comment);
		
		System.out.println("Comentarios del ShoppingCart después de añadir un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		System.out.println("ShoppingCartServiceTest - testAddComment1 - FinishPoint");

	}

	@Test
	public void testRemoveComment1(){
		System.out.println("ShoppingCartServiceTest - testRemoveComment1 - StartPoint");
		ShoppingCart shoppingCart;
		String comment;
		Boolean result;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		comment = shoppingCart.getComments().iterator().next();
		
		System.out.println("Comentarios del ShoppingCart antes de borrar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		shoppingCart.removeComment(comment);
		
		System.out.println("Comentarios del ShoppingCart después de borrar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		System.out.println("ShoppingCartServiceTest - testRemoveComment1 - FinishPoint");

	}

	@Test
	public void testModifyComment1(){
		System.out.println("ShoppingCartServiceTest - testModifyComment1 - StartPoint");
		ShoppingCart shoppingCart;
		String commentOld;
		String commentNew;
		Boolean result;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		commentOld = shoppingCart.getComments().iterator().next();
		commentNew = "Comentario modificado";
		
		System.out.println("Comentarios del ShoppingCart antes de borrar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		shoppingCart.modifyComment(shoppingCart, commentOld, commentNew);
		
		System.out.println("Comentarios del ShoppingCart después de borrar un comentario:");
		for(String s: shoppingCart.getComments()){
			System.out.println(s);
		}
		
		System.out.println("ShoppingCartServiceTest - testModifyComment1 - FinishPoint");

	}	
	
	// Requisito 11.7
	@Test
	public void testCheckOut1(){
		System.out.println("ShoppingCartServiceTest - testAddItem1 - StartPoint");
		ShoppingCart shoppingCart;
		Consumer consumer;
		Boolean result;
		
		shoppingCart = shoppingCartService.findAll().iterator().next();
		consumer = shoppingCart.getConsumer();
		
		System.out.println("Lista de Items de ShoppingCart antes del checkout:");
		
		for(Content c: consumer.getShoppingCart().getContents()){
			System.out.println(c.getItem() + ", " + c.getUnits());
		}
		
		for(Order o: consumer.getOrders()){
			System.out.println(o.getTicker());
		}
		
		shoppingCartService.checkOut(consumer.getShoppingCart());
		
		System.out.println("Lista de Items de ShoppingCart después del checkout:");
		for(Order o: consumer.getOrders()){
			System.out.println(o.getTicker());
		}
		
		System.out.println("ShoppingCartServiceTest - testAddItem1 - FinishPoint");

	}
	
}
