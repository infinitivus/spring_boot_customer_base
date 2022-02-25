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

    //Создание нового клиента
    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        ResponseEntity<String> resp = null;
        try {
            Person pers = personService.savePerson(person);
            resp = new ResponseEntity<String>(
                    "Person '" + person.getId() + "' created:" + pers.toString(), HttpStatus.CREATED); //201
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to save person",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }

    // Вывод всех пользователей с ролями на консоль
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPerson() {
        ResponseEntity<?> resp = null;
        try {
            List<Person> list = personService.allPerson();
            resp = new ResponseEntity<List<Person>>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get all person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    @GetMapping("/getAllSort/sort")
    public ResponseEntity<?> getAllSortPerson(@PathVariable String sort) {
        ResponseEntity<?> resp = null;
        try {
            List<Person> list = personService.sortPerson(sort);
            resp = new ResponseEntity<List<Person>>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get all sort person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    @GetMapping("/getAllSearch/search")
    public ResponseEntity<?> getAllSearchPerson(@PathVariable String search) {
        ResponseEntity<?> resp = null;
        try {
            List<Person> list = personService.searchPerson(search);
            resp = new ResponseEntity<List<Person>>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get all search person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // получение одного клиента по id
    @GetMapping("/getPerson/{id}")
    public ResponseEntity<?> getOnePerson(@PathVariable Integer id) {
        ResponseEntity<?> resp = null;
        try {
            Person person = personService.getPerson(id);
            resp = new ResponseEntity<Person>(person, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to find person",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // Удаление пользователя ok
    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
        ResponseEntity<String> resp = null;
        try {
            personService.deletePerson(id);
            resp = new ResponseEntity<String>(
                    "Person '" + id + "' deleted", HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to delete person", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //  изменение данных клиента по id
    @PatchMapping(path = "/modify/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        ResponseEntity<String> resp = null;
        try {
            Person person = personService.getPerson(id);
            Person personPatched=applyPatchToPerson(patch,person);
            personService.savePerson(personPatched);
            resp = new ResponseEntity<String>(
                    "Person '" + personPatched.getId() + "' updated:",
                    HttpStatus.PARTIAL_CONTENT); //206
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to update person",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }

    private Person applyPatchToPerson(JsonPatch patch, Person person) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(person, JsonNode.class));
        return objectMapper.treeToValue(patched, Person.class);
    }
}

//    // Редактирование данных пользователя
//    @PutMapping("/modify/{id}")
//    public ResponseEntity<String> modifyUser(@RequestBody UserData user,@PathVariable Integer id) {
//        ResponseEntity<String> resp = null;
//        try {
//            userService.updateUser(user,id);
//            resp = new ResponseEntity<String>(
//                    "User '"+ id+"' updated",
//                    HttpStatus.RESET_CONTENT); //205
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp = new ResponseEntity<String>(
//                    "Unable to update user",
//                    HttpStatus.INTERNAL_SERVER_ERROR); //500
//        }
//        return resp;
//    }

/**
 * To update one Invoice just like where clause condition, updates Invoice object & returns Status as ResponseEntity<String>
 */
//    @PatchMapping("/modify/{id}/{number}")
//    public ResponseEntity<String> updateInvoiceNumberById(
//            @PathVariable Long id,
//            @PathVariable String number
//    ) {
//        ResponseEntity<String> resp = null;
//        try {
//            service.updateInvoiceNumberById(number, id);
//            resp = new ResponseEntity<String>(
//                    "Invoice '" + number + "' Updated",
//                    HttpStatus.PARTIAL_CONTENT); //206- Reset-Content(PUT)
//
//        } catch (InvoiceNotFoundException pne) {
//            throw pne; // re-throw exception to handler
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp = new ResponseEntity<String>(
//                    "Unable to Update Invoice",
//                    HttpStatus.INTERNAL_SERVER_ERROR); //500-ISE
//        }
//        return resp;
//    }














