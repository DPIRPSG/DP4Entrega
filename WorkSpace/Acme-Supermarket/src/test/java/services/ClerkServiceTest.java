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

import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;
import domain.Clerk;
import domain.Message;
import domain.Order;

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
		System.out.println("Lista de Clerks antes de la creación de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		userAccount = userAccountService.createComplete("Clerk99", "91ec1f9322200048c9496d036a694f86", "CLERK");
		
		result = clerkService.create();
	
		result.setName("Manuel");
		result.setEmail("manuel@mail.com");
		result.setPhone("666123123");
		result.setSurname("García");
		result.setUserAccount(userAccount);
		result.setReceived(received);
		result.setSent(sent);
		result.setOrders(orders);

		clerkService.save(result);
		
		all = clerkService.findAll();
		System.out.println("Lista de Clerks después de la creación de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		System.out.println("ClerkServiceTest - testClerk1 - FinishPoint");
	}

	@Test
	public void testClerkServedMoreOrders1(){
		System.out.println("ClerkServiceTest - testClerkServedMoreOrders1 - StartPoint");
		
		Collection<Clerk> all;
		
		authenticate("admin");
		
		all = clerkService.findClerkServedMoreOrders();
		for(Clerk c:all){
			System.out.println(c.getName() + " " + c.getSurname());
		}
		
		authenticate(null);
		
		System.out.println("ClerkServiceTest - testClerkServedMoreOrders1 - FinishPoint");
	}
	
	@Test
	public void testClerkServedLessOrders1(){
		System.out.println("ClerkServiceTest - testClerkServedLessOrders1 - StartPoint");
		
		Collection<Clerk> all;
		
		authenticate("admin");
		
		all = clerkService.findClerkServedLessOrders();
		for(Clerk c:all){
			System.out.println(c.getName() + " " + c.getSurname());
		}
		
		authenticate(null);
		
		System.out.println("ClerkServiceTest - testClerkServedLessOrders1 - FinishPoint");
	}
}
