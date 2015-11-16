package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
	
	// Test ---------------------------------------
	@Test
	public void testConsumerCancelledMoreOrders1(){
		System.out.println("ConsumerServiceTest - testConsumerCancelledMoreOrders1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findConsumerMoreOrdersCancelled();
		for(Consumer c:all){
			System.out.println(c.getName() + " " + c.getSurname());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testConsumerCancelledMoreOrders1 - FinishPoint");
	}
	
	@Test
	public void testConsumerCancelledLessOrders1(){
		System.out.println("ConsumerServiceTest - testConsumerCancelledLessOrders1 - StartPoint");
		
		Collection<Consumer> all;
		
		authenticate("admin");
		
		all = consumerService.findConsumerLessOrdersCancelled();
		for(Consumer c:all){
			System.out.println(c.getName() + " " + c.getSurname());
		}
		
		authenticate(null);
		
		System.out.println("ConsumerServiceTest - testConsumerCancelledLessOrders1 - FinishPoint");
	}
}
