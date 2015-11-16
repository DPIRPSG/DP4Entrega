package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Clerk;

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
	
	// Test ---------------------------------------
	@Test
	public void testCreate1(){
		//Peta. Hace falta modificar los valores con los set
		System.out.println("Requisito 17.1 - Register a new clerk to the system.");
		System.out.println("ClerkServiceTest - testClerk1 - StartPoint");
		
		Clerk result;
		Collection<Clerk> all;
		
		authenticate("admin");
		
		all = clerkService.findAll();
		System.out.println("Lista de Clerks antes de la creación de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		result = clerkService.create();
		clerkService.save(result);
		
		all = clerkService.findAll();
		System.out.println("Lista de Clerks después de la creación de otro");
		for(Clerk c:all){
			System.out.println(c.getName());
		}
		
		authenticate(null);
		System.out.println("ClerkServiceTest - testClerk1 - FinishPoint");
	}
}
