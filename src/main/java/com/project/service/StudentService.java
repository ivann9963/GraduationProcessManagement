package com.project.service;

import com.project.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student studentDto);

    void deleteById(Long id);
}
