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

import domain.Actor;
import domain.Clerk;
import domain.Consumer;
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
	@Autowired
	private MessageService messageService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private ConsumerService consumerService;
	
	// Test ---------------------------------------
	@Test
	public void testCreate1(){
		//Peta. Hace falta modificar los valores con los set
		System.out.println("Requisito 17.1 - Register a new clerk to the system.");
		System.out.println("ClerkServiceTest - testClerk1 - StartPoint");
		
		Clerk result;
		Collection<Clerk> all;
		Actor actor;
		Consumer consumer;
		Collection<Order> orders;
		UserAccount userAccount;
		Collection<Message> received;
		Collection<Message> sent;
		
		authenticate("admin");
		
		all = clerkService.findAll();
		System.out.println("Lista de Clerks antes de la creación de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		result = clerkService.create();
//		actor = actorService.findByPrincipal();
		userAccount = new UserAccount();
		userAccount.setUsername("NuevoUser");
		userAccount.setPassword("NuevaPassword");
		
		result.setUserAccount(userAccount);
		
//		//NotNull en Clerk
//		orders = Collections.emptyList();
//		result.setOrders(orders);
//		
//		result.setSurname("Apellido nuevo");
//		
//		userAccount = result.getUserAccount();
//		userAccount.setUsername("UsernameNuevo");
//		userAccount.setPassword("PasswordNueva");
//		result.setUserAccount(userAccount);
//		
//		result.setName("Nombre nuevo");
//		
//		received = Collections.emptyList();
//		result.setReceived(received);
//		
//		sent = Collections.emptyList();
//		result.setSent(sent);
//		
//		result.setEmail("manolo@gamil.com");
		
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
