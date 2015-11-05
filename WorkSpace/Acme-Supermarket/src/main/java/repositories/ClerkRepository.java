package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Clerk;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk, Integer> {

}
