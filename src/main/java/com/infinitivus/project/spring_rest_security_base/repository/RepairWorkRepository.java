package com.infinitivus.project.spring_rest_security_base.repository;

import com.infinitivus.project.spring_rest_security_base.entity.person.RepairWork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairWorkRepository extends CrudRepository<RepairWork,Integer> {
}
