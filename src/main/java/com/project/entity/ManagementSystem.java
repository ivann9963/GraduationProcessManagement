package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="management_system")
public class ManagementSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "student_id")
    private Set<User> students;

    @OneToMany
    @JoinColumn(name="teacher_id")
    private Set<User> teachers;

    @OneToMany
    @JoinColumn(name = "management_system_id")
    private Set<DiplomaAcceptance> diplomaAcceptances;

}
