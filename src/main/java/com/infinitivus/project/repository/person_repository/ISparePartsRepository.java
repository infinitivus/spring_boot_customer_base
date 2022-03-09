package com.infinitivus.project.repository.person_repository;

import com.infinitivus.project.entity.person.SpareParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISparePartsRepository extends JpaRepository<SpareParts,Integer> {

    List<SpareParts> findByNameSparePartOrArticle
            (String nameSparePart,String article);
}
