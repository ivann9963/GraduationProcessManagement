package com.project.controller;

import com.project.dto.ThesisDto;
import com.project.dto.ThesisReviewDto;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;
import com.project.service.ThesisService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thesis")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @GetMapping
    public ResponseEntity<?> getAllTheses() {
        return ResponseEntity.ok(thesisService.getAllTheses());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getThesesById(@PathVariable Long id) {
        return ResponseEntity.ok(thesisService.getThesesById(id));
    }

    @PostMapping("/upload-thesis")
    public ResponseEntity<?> uploadThesis(@RequestBody ThesisDto thesisDto) {
        try {
            ThesisDto uploadedThesis = thesisService.uploadThesis(thesisDto);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authentication: " + auth);
            return ResponseEntity.ok(uploadedThesis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/process-thesis")
    public ResponseEntity<?> processThesis(@RequestBody ThesisReviewDto thesisReviewDto) {
        try {
            ThesisReview thesisReview = new ThesisReview();
            Thesis thesis = thesisService.findById(thesisReviewDto.getThesisId())
                    .orElseThrow(() -> new EntityNotFoundException("Thesis not found with id: " + thesisReviewDto.getThesisId()));
            thesisReview.setThesis(thesis);
            thesisReview.setText(thesisReviewDto.getText());
            thesisReview.setConclusion(thesisReviewDto.getConclusion());


            thesisService.processThesis(thesisReview);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



}
