package com.project.entity;

import com.project.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student extends User {

    private String name;

    private String facultyNumber;

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }

    public Student() {
        super.setRole(Role.STUDENT);
    }


}
