package com.example.currenda.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;

}