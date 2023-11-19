package com.project.controller;

import com.project.entity.DiplomaWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.service.DiplomaWorkService;

import java.util.List;

@RestController
@RequestMapping("/api/diploma-works")
public class DiplomaWorkController {

    private final DiplomaWorkService diplomaWorkService;

    @Autowired
    public DiplomaWorkController(DiplomaWorkService diplomaWorkService) {
        this.diplomaWorkService = diplomaWorkService;
    }

    @GetMapping
    public List<DiplomaWork> getAllDiplomaWorks() {
        return diplomaWorkService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiplomaWork> getDiplomaWorkById(@PathVariable Long id) {
        return diplomaWorkService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DiplomaWork createDiplomaWork(@RequestBody DiplomaWork diplomaWork) {
        return diplomaWorkService.save(diplomaWork);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiplomaWork> updateDiplomaWork(@PathVariable Long id, @RequestBody DiplomaWork diplomaWork) {
        return diplomaWorkService.findById(id)
                .map(existingWork -> {
                    // Set the necessary fields from diplomaWork to existingWork
                    return ResponseEntity.ok(diplomaWorkService.save(existingWork));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiplomaWork(@PathVariable Long id) {
        return diplomaWorkService.findById(id)
                .map(existingWork -> {
                    diplomaWorkService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
