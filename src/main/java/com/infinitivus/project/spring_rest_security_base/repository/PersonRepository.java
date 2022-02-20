package com.infinitivus.project.spring_rest_security_base.repository;

import com.infinitivus.project.spring_rest_security_base.entity.person.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer>{
}