package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("select (count(distinct o1)*1.0)/(count(distinct o2)*1.0) from Order o1, Order o2 where month(o1.cancelMoment) = month(CURRENT_DATE) and year(o1.cancelMoment) = year(CURRENT_DATE) and month(o2.placementMoment) = month(CURRENT_DATE) and year(o2.placementMoment) = year(CURRENT_DATE);")
	double rateOrderCancelled();
}
