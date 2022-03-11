package com.infinitivus.project.repository.person_repository;

import com.infinitivus.project.entity.person.RepairWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepairWorkRepository extends JpaRepository<RepairWork,Integer> {
}
