package com.project.controller;

import com.project.dto.TeacherDto;
import com.project.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
        Teacher tmp = teacherService.findById(id).get();
        tmp.setPosition(teacherDto.getPosition());
        tmp.setName(teacherDto.getName());
        Teacher t = teacherService.save(tmp);
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(existingTeacher -> {
                    teacherService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
