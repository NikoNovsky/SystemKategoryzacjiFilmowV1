package com.example.currenda.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "authority")
    private String authority;
}