package com.epam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Entity
public class Admin {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "password")
    private String pass;
}
