package com.infinitivus.project.spring_rest_security_base.repository.person_repository;

import com.infinitivus.project.spring_rest_security_base.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Integer> {

    @Query("from Person where surname=?1 or name=?1 or phoneNumber=?1 or email=?1")
    public List<Person> searchPerson(String searchLine);

    @Query("from Person order by ?1 ")
    public List<Person> sortPerson(String sortLine);
}