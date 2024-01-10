package com.project.controller;

import com.project.entity.Thesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.service.ThesisService;

import java.util.List;

@RestController
@RequestMapping("/api/diploma-works")
public class DiplomaWorkController {

    private final ThesisService thesisService;

    @Autowired
    public DiplomaWorkController(ThesisService thesisService) {
        this.thesisService = thesisService;
    }

    @GetMapping
    public List<Thesis> getAllDiplomaWorks() {
        return thesisService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thesis> getDiplomaWorkById(@PathVariable Long id) {
        return thesisService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Thesis createDiplomaWork(@RequestBody Thesis thesis) {
        return thesisService.save(thesis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Thesis> updateDiplomaWork(@PathVariable Long id, @RequestBody Thesis thesis) {
        return thesisService.findById(id)
                .map(existingWork -> {
                    // Set the necessary fields from diplomaWork to existingWork
                    return ResponseEntity.ok(thesisService.save(existingWork));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiplomaWork(@PathVariable Long id) {
        return thesisService.findById(id)
                .map(existingWork -> {
                    thesisService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
