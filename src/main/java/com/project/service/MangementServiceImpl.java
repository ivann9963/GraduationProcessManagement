package com.project.service;

import com.project.entity.ManagementSystem;
import com.project.repository.ManagementSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MangementServiceImpl implements ManagementService {
    private final ManagementSystemRepository managementSystemRepository;

    @Autowired
    public MangementServiceImpl(ManagementSystemRepository managementSystemRepository) {
        this.managementSystemRepository = managementSystemRepository;
    }

    @Override
    public ManagementSystem save(ManagementSystem managementSystem) {
        return managementSystemRepository.save(managementSystem);
    }

    /*** Как ще проверяваме дали текущият user е Студент или преподавател */
    @PreAuthorize("hasRole('TEACHER')")
    public void processThesis() {
        // Logic for processing thesis
    }

    @PreAuthorize("hasRole('STUDENT')")
    public void uploadThesis() {
        // Logic for uploading thesis
    }


}
