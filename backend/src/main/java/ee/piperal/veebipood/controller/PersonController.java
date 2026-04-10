package ee.piperal.veebipood.controller;

import ee.piperal.veebipood.dto.PersonLoginDto;
import ee.piperal.veebipood.dto.PersonLoginRecordDto;
import ee.piperal.veebipood.entity.Person;
import ee.piperal.veebipood.entity.Product;
import ee.piperal.veebipood.repository.PersonRepository;
import ee.piperal.veebipood.repository.ProductRepository;
import ee.piperal.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    //Dependency injection, if this class (PersonController) is created, is connected at the same time
    @Autowired
    private PersonService personService;

    @GetMapping("person")
    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @DeleteMapping("person/{id}")
    public List<Person> delPerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person) {
        personService.validate(person);
        return personRepository.save(person);
    }

    @PostMapping("login")
    public Person login(@RequestBody Person person) {
        Person dbperson = personRepository.findByEmail(person.getEmail());
        if (dbperson == null) {
            throw new RuntimeException("Invalid email");
        }
        if (!dbperson.getPassword().equals(person.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return dbperson;
    }

}
