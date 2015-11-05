package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Clerk;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk, Integer> {

	@Query("select c1 from Order o1 join o1.clerk c1 where o1.deliveryMoment is not null group by c1 having count(o1) >= all(select count(o2) from Clerk c2 join c2.orders o2 where o2.deliveryMoment is not null group by c2);")
	Collection<Clerk> clerkServerMoreOrders();
	
	@Query("select c1 from Order o1 join o1.clerk c1 where o1.deliveryMoment is not null group by c1 having count(o1) <= all(select count(o2) from Clerk c2 join c2.orders o2 where o2.deliveryMoment is not null group by c2);")
	Collection<Clerk> clerkServerLessOrders();
}
