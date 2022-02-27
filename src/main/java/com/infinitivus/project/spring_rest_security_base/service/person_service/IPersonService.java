package com.infinitivus.project.spring_rest_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_rest_security_base.entity.person.Person;

import java.util.List;

public interface IPersonService {

    Person savePerson(Person person);

    Person getPerson(Integer id);

    List<Person> allPerson();

   List<Person> searchPerson(String line);

    List<Person> sortPerson(String field);

    void deletePerson(Integer id);

   Person applyPatchToPerson(JsonPatch patch, Person person) throws JsonPatchException, JsonProcessingException;
}
