package com.infinitivus.project.repository.security_repository;

import com.infinitivus.project.entity.security.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserData,Integer> {

        UserData findByUsername(String username);
}
