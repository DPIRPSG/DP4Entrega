package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Clerk;
import domain.Folder;
import domain.Message;
import domain.Order;

import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ClerkServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private ClerkService clerkService;
	@Autowired
	private UserAccountService userAccountService;
	
	// Test ---------------------------------------
	@Test
	public void testCreate1(){
		//Peta. Hace falta modificar los valores con los set
		System.out.println("Requisito 17.1 - Register a new clerk to the system.");
		System.out.println("ClerkServiceTest - testClerk1 - StartPoint");
		
		Clerk result;
		Collection<Clerk> all;
		UserAccount userAccount;
		Collection<Message> received;
		Collection<Message> sent;
		Collection<Order> orders;
		
		received = new ArrayList<Message>();
		sent = new ArrayList<Message>();
		orders = new ArrayList<Order>();
		
		authenticate("admin");
		
		all = clerkService.findAll();
		System.out.println("Lista de Clerks antes de la creaci�n de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		userAccount = userAccountService.createComplete("Clerk99", "91ec1f9322200048c9496d036a694f86", "CLERK");
		
		result = clerkService.create();
		
		result.setName("Manuel");
		result.setEmail("manuel@mail.com");
		result.setPhone("666123123");
		result.setSurname("Garc�a");
		result.setUserAccount(userAccount);
		result.setReceived(received);
		result.setSent(sent);
		result.setOrders(orders);


		clerkService.save(result);
		
		all = clerkService.findAll();
		System.out.println("Lista de Clerks despu�s de la creaci�n de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		System.out.println("ClerkServiceTest - testClerk1 - FinishPoint");
	}
}
