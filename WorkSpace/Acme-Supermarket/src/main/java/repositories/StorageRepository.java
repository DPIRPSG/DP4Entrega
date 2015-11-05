package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {

}
