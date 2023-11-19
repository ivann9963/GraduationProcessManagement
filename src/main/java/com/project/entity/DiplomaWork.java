package com.project.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "diploma_works")
public class DiplomaWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String objective;

    @Column(nullable = false)
    private String tasks;

    @Column(nullable = false)
    private String technologies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;

    // Getters and setters omitted for brevity
}
