package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Order;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class OrderServiceTest extends AbstractTest{

	// Service under test -------------------------
	@Autowired
	private OrderService orderService;
	
	// Test ---------------------------------------
	@Test
	public void testList1(){
		System.out.println("Requisito 12.6 - List the consumers that are registered in the system.");
		System.out.println("ConsumerServiceTest - testList1 - StartPoint");
		
		Collection<Order> all;
		
		all = orderService.findAll();
		
		System.out.println("Lista de order placed en el sistema");
		
		for(Order o:all){
			System.out.println(o.getTicker() + ", " + o.getPlacementMoment());
		}
		
		System.out.println("ConsumerServiceTest - testList1 - FinishPoint");
	}
	
	@Test
	public void testCancelOrder1(){
		System.out.println("Requisito 16.1 - Cancel an order, as long a no clerk has self-assigned it.");
		System.out.println("OrderServiceTest - testCancelOrder1 - StartPoint");
		
		Order order;
		Collection<Order> all;
		
		authenticate("consumer1");
		
		order = null;
		
		all = orderService.findAll();
		
		for(Order o:all){
			if(o.getClerk()==null){
				order = o;
				break;
			}
		}
		
		System.out.println("Fecha de cancelación de la order antes de cancelarla:");
		System.out.println(order.getCancelMoment());

		orderService.cancelOrder(order);
		
		System.out.println("Fecha de cancelación de la order después de cancelarla:");
		System.out.println(order.getCancelMoment());
		
		authenticate(null);
		
		System.out.println("OrderServiceTest - testCancelOrder1 - FinishPoint");
	}
	
	@Test
	public void testOrderRatio1(){
		System.out.println("OrderServiceTest - testOrderRatio1 - StartPoint");
		
		double ratio;
		
		authenticate("admin");
		
		ratio = orderService.rateOrderCancelled();
		System.out.println("Ratio: " + ratio);
		
		authenticate(null);
		
		System.out.println("OrderServiceTest - testOrderRatio1 - FinishPoint");
	}
}
