package se.verran.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import se.verran.userservice.VO.CarVO;
import se.verran.userservice.entities.Person;
import se.verran.userservice.repositories.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Value("${server.port}")
    String port;
    int counter = 0;

    private RestTemplate restTemplate;
    private PersonRepository personRepository;
    @Autowired
    public PersonController(PersonRepository personRepository, RestTemplate restTemplate) {
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> fetchAllPersons(){
        return ResponseEntity.ok(personRepository.findAll());
    }
    @GetMapping("/showcar")
    public ResponseEntity<List<CarVO>> showCar(){

        System.out.println(
                "Here goes the instance running on port " +
                        port +
                        " with count: " + ++counter);
        // For CAR-SERVICE to work, either DiscoveryClient OR @LoadBalanced is needed.
        String url = "http://CAR-SERVICE/cars/all";
        ResponseEntity<List<CarVO>> carsVO = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarVO>>() {});
        return carsVO;
    }
}
