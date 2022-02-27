package com.infinitivus.project.spring_rest_security_base.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_rest_security_base.entity.person.Person;
import com.infinitivus.project.spring_rest_security_base.service.person_service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private IPersonService personService;

    //Создание нового клиента ok
    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        ResponseEntity<String> resp;
        try {
            Person pers = personService.savePerson(person);
            resp = new ResponseEntity<>(
                    "Person '" + person.getId() + "' created:" + pers.toString(), HttpStatus.CREATED); //201
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to save person",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }

    // Вывод всех клиентов на консоль ok
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPerson() {
        ResponseEntity<?> resp;
        try {
            List<Person> list = personService.allPerson();
            resp = new ResponseEntity<>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // Вывод всех отсортированных клиентов по полю(name,surname,email,) ok
    // http://localhost:8080/persons/sort/surname
    @GetMapping("/sort/{line}")
    public ResponseEntity<?> getAllSortPerson(@PathVariable String line) {
        ResponseEntity<?> resp;
        try {
            List<Person> list = personService.sortPerson(line);
            resp = new ResponseEntity<>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all sort person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

 //    Поск клиента по строке
    @GetMapping("/search/{line}")
    public ResponseEntity<?> getAllSearchPerson(@PathVariable String line) {
        ResponseEntity<?> resp;
        try {
            List<Person> list = personService.searchPerson(line);
            resp = new ResponseEntity<>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all search person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // получение одного клиента по id ok
    @GetMapping("/getPerson/{id}")
    public ResponseEntity<?> getOnePerson(@PathVariable Integer id) {
        ResponseEntity<?> resp;
        try {
            Person person = personService.getPerson(id);
            resp = new ResponseEntity<>(person, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to find person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // Удаление клиента  ok
    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
        ResponseEntity<String> resp;
        try {
            personService.deletePerson(id);
            resp = new ResponseEntity<>(
                    "Person '" + id + "' deleted", HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to delete person", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //  изменение данных клиента по id  ok
    @PatchMapping(path = "/modify/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        ResponseEntity<String> resp ;
        try {
            Person person = personService.getPerson(id);
            Person personPatched=personService.applyPatchToPerson(patch,person);
            personService.savePerson(personPatched);
            resp = new ResponseEntity<>(
                    "Person '" + personPatched.getId() + "' updated:"+ personPatched,
                    HttpStatus.PARTIAL_CONTENT); //206
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to update person",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }
}















