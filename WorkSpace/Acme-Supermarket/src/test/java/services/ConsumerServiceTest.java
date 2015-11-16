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
public class ConsumerServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private UserAccountService userAccountService;
	
	// Test ---------------------------------------
	@Test
	public void testCreate1(){
		System.out.println("Requisito 10.1 - Register a new consumer to the system.");
		System.out.println("ConsumerServiceTest - testConsumer1 - StartPoint");
		
		Consumer result;
		Collection<Consumer> all;
		UserAccount userAccount;
		Collection<Message> received;
		Collection<Message> sent;
		Collection<Order> orders;
		
		received = new ArrayList<Message>();
		sent = new ArrayList<Message>();
		orders = new ArrayList<Order>();
		
		
		all = consumerService.findAll();
		System.out.println("Lista de Consumers antes de la creación de otro");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		
		userAccount = userAccountService.createComplete("Consumer99", "91ec1f9333300048c9496d036a694f86", "CONSUMER");
		
		result = consumerService.create();
		
		result.setName("Fatima");
		result.setEmail("fatima@mail.com");
		result.setPhone("666123321");
		result.setSurname("Caballero");
		result.setUserAccount(userAccount);
		result.setReceived(received);
		result.setSent(sent);
		result.setOrders(orders);



		consumerService.save(result);
		
		all = consumerService.findAll();
		System.out.println("Lista de Consumers después de la creación de otro");
		for(Consumer c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		System.out.println("ConsumerServiceTest - testConsumer1 - FinishPoint");
	}
}
