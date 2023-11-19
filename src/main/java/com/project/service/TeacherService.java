package com.project.service;

import com.project.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService{
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher teacher);
    void deleteById(Long id);
}