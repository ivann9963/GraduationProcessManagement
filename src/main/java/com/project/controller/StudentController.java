package com.project.controller;

import com.project.dto.StudentDto;
import com.project.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.findById(id)
                .map(existingStudent -> {
                    studentService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Student tmp = studentService.findById(id).get();
        tmp.setFacultyNumber(studentDto.getFacultyNumber());
        tmp.setName(studentDto.getName());
        Student s = studentService.save(tmp);
        return ResponseEntity.ok(s);
    }
}
