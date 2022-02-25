package com.infinitivus.project.spring_rest_security_base.service.person_service;

import com.infinitivus.project.spring_rest_security_base.entity.person.Person;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

public interface IPersonService {

    Person savePerson(Person person);

    Person getPerson(Integer id);

    List<Person> allPerson();

    List<Person> searchPerson(String searchLine);

    List<Person> sortPerson(String sortLine);

    void deletePerson(Integer id);

//    Person applyPatchToPerson(JsonPath patch, Person person);
}
