package com.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    public enum Position {
        ASSISTANT, SENIOR_ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR
    }

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

}
