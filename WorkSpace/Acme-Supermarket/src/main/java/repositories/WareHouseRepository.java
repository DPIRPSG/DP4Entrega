package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.WareHouse;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {

}
