package com.example.hotelopinionapp.repository;

import com.example.hotelopinionapp.model.Opinion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Integer> {

    List<Opinion> findAll();
}
