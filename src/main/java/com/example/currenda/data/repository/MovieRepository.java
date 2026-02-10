package com.example.currenda.data.repository;

import com.example.currenda.data.model.Movies;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MovieRepository extends CrudRepository<Movies, Integer> {

    public List<Movies> findByUserId(String userId);
}
