package com.example.currenda.data.repository;


import com.example.currenda.data.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    public User findByUsername(String username);
}
