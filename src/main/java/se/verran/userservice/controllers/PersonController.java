package se.verran.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.verran.userservice.entities.Person;
import se.verran.userservice.repositories.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonRepository personRepository;
    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> fetchAllPersons(){
        return ResponseEntity.ok(personRepository.findAll());
    }
}
