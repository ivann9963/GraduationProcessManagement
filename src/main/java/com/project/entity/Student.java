package com.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student extends User {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String facultyNumber;

}
