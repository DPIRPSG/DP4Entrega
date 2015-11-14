package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("select (count(distinct o1)*1.0)/(count(distinct o2)*1.0) from Order o1, Order o2 where month(o1.cancelMoment) = month(CURRENT_DATE) and year(o1.cancelMoment) = year(CURRENT_DATE) and month(o2.placementMoment) = month(CURRENT_DATE) and year(o2.placementMoment) = year(CURRENT_DATE)")
	double rateOrderCancelled();
	
	//deben estar ordenadas siendo la primera la más antigua
	@Query("select o from Order o where o.clerk is null and o.cancelMoment is null order by o.placementMoment")
	Collection<Order> findAllNotAssigned();
}
