package com.infinitivus.project.spring_rest_security_base.repository;

import com.infinitivus.project.spring_rest_security_base.entity.MobileHome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileHomeRepository extends CrudRepository<MobileHome,Integer> {
}
