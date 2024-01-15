package com.project.controller;

import com.project.dto.ThesisUploadDto;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;
import com.project.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thesis")
public class ThesisController {

    private final ThesisService thesisService;

    @Autowired
    public ThesisController(ThesisService thesisService) {
        this.thesisService = thesisService;
    }

    @PostMapping("/upload-thesis")
    public ResponseEntity<?> uploadThesis(@RequestBody ThesisUploadDto thesis) {
        try {
            Thesis uploadedThesis = thesisService.uploadThesis(thesis);
            return ResponseEntity.ok(uploadedThesis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



    @PostMapping("/process-thesis")
    public ResponseEntity<?> processThesis(@RequestBody ThesisReview thesisReview) {
        try {
            Long thesisId = thesisReview.getThesis().getId(); // Extract thesisId from ThesisReview
            thesisService.processThesis(thesisId, thesisReview);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



}
