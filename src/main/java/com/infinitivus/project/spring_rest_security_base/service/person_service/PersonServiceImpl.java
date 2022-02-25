package com.infinitivus.project.spring_rest_security_base.service.person_service;

import com.infinitivus.project.spring_rest_security_base.entity.person.Person;
import com.infinitivus.project.spring_rest_security_base.repository.person_repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
      return  personRepository.save(person);
    }

    @Override
    public Person getPerson(Integer id) {
        return personRepository.findById(id).get();

    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> searchPerson(String searchLine) {
        return personRepository.searchPerson(searchLine);
    }

    @Override
    public List<Person> sortPerson(String sortLine) {
        return personRepository.sortPerson(sortLine);
    }
}
