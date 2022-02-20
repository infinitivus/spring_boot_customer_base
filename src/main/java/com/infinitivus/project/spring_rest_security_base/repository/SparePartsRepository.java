package com.infinitivus.project.spring_rest_security_base.repository;

import com.infinitivus.project.spring_rest_security_base.entity.person.SpareParts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartsRepository extends CrudRepository<SpareParts,Integer> {
}
