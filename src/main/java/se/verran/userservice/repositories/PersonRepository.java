package se.verran.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.verran.userservice.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
