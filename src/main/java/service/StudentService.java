package service;

import entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);

    void deleteById(Long id);
}
