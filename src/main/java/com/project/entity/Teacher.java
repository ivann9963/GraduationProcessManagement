package com.project.entity;

import com.project.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teachers")
public class Teacher extends User{

    private String name;

    @Enumerated(EnumType.STRING)

    private Position position;

    @Override
    public Role getRole() {
        return Role.TEACHER;
    }

    public enum Position {
        ASSISTANT, SENIOR_ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR
    }

}
