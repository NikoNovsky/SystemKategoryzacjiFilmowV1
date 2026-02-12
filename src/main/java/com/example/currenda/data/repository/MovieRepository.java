package com.example.currenda.data.repository;

import com.example.currenda.data.model.Movies;
import com.example.currenda.data.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MovieRepository extends CrudRepository<Movies, Integer> {

    public List<Movies> findByUserUsername(String username);
    boolean existsByTitleAndUser(String title, User user);
    List<Movies> findAllByIdInAndUser(List<Integer> ids, User user);
}
