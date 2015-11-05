package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

}
