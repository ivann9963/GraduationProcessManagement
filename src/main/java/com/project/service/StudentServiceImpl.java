package com.project.service;

import com.project.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
