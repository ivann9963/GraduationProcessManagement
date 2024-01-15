package com.project.controller;

import com.project.entity.Thesis;
import com.project.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/management-system")
public class ManagementSystemController {

    private final ManagementService managementService;

    @Autowired
    public ManagementSystemController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/upload-thesis")
    public ResponseEntity<?> uploadThesis(@RequestBody Thesis thesis) {
        try {
            Thesis uploadedThesis = managementService.uploadThesis(thesis);
            return ResponseEntity.ok(uploadedThesis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/process-thesis")
    public void processThesis(@RequestBody Thesis thesis) {
        // Call service method to process the thesis
        managementService.processThesis(thesis);
    }

}
