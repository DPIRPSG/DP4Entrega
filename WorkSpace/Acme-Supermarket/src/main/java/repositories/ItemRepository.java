package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("select i from Item i where i.deleted is false and i.sku IN (select oi.sku from OrderItem oi join oi.order o where o.deliveryMoment is not null group by oi.sku having max(oi.units) = (select max(oi.units) from OrderItem oi));")
	Collection<Item> findItemBestSelling();
	
	@Query("select i from Item i where i.deleted is false and i.sku IN (select oi.sku from OrderItem oi join oi.order o where o.deliveryMoment is not null group by oi.sku having min(oi.units) = (select min(oi.units) from OrderItem oi));")
	Collection<Item> findItemWorstSelling();
}
