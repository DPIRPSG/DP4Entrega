package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("select i from Item i where i.deleted is false and i.sku IN (select oi.sku from OrderItem oi join oi.order o where o.deliveryMoment is not null group by oi.sku having max(oi.units) = (select max(oi.units) from OrderItem oi))")
	Collection<Item> findItemBestSelling();
	
	@Query("select i from Item i where i.deleted is false and i.sku IN (select oi.sku from OrderItem oi join oi.order o where o.deliveryMoment is not null group by oi.sku having min(oi.units) = (select min(oi.units) from OrderItem oi))")
	Collection<Item> findItemWorstSelling();
	
	@Query("select i from Item i where i.deleted is false and i.comments.size = (select max(i.comments.size) from Item i where i.deleted is false) group by i")
	Collection<Item> findItemMoreComments();
	
	@Query("select i from Item i where i.deleted = false")
	Collection<Item> findAllNotDeleted();
	
	@Query("select i from Item i where i.deleted = false and i.category = ?1")
	Collection<Item> findAllByCategoryId(int categoryId);
	
	@Query("select i from Item i join i.storages s where i.deleted = false and s.wareHouse.id = ?1")
	Collection<Item> findAllByWareHouseId(int wareHouseId);
	
	@Query("select i from Item i where i.category.tax = ?1")
	Collection<Item> findByTaxId(int taxId);
	
	@Query("select i from Item i where i.deleted = false and i.sku like '%?1%' or i.name like '%?1%' or i.description like '%?1%'")
	Collection<Item> findBySingleKeyword(String keyword);
	
	@Query("select c.item from ShoppingCart s join s.contents c where c.item.deleted = false and s.id = ?1")
	Collection<Item> findAllByShoppingCartId(int shoppingCartId);
}
