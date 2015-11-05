package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

	@Query("select c from Consumer c join c.orders o group by c having count(c.orders.size) = (select max(c.orders.size) from Consumer c);")
	Collection<Consumer> findConsumerMoreOrders();
	
	@Query("select c from Consumer c join c.orders o where o.cancelMoment is null group by c having max(o.amount) = (select max(o.amount) from Order o);")
	Collection<Consumer> findConsumerSpentMoreMoney();
	}
