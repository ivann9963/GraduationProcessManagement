package com.project.service;

import com.project.entity.Thesis;
import com.project.entity.ManagementSystem;
import com.project.repository.ManagementSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    /*** Как ще проверяваме дали текущият user е Студент или преподавател */
    @PreAuthorize("hasRole('TEACHER')")
    public void processThesis(Thesis thesis) {
        // Logic for processing thesis
    }

    @Override
    @PreAuthorize("hasRole('STUDENT')")
    public Thesis uploadThesis(Thesis thesis) {
        return null;
    }

    @Override
    public ManagementSystem createOrUpdateManagementSystem(ManagementSystem managementSystem) {
        Optional<ManagementSystem> existingSystem = managementSystemRepository.findFirstByOrderByIdAsc();
        return existingSystem.map(system -> updateExistingManagementSystem(system, managementSystem))
                .orElseGet(() -> managementSystemRepository.save(managementSystem));
    }

    private ManagementSystem updateExistingManagementSystem(ManagementSystem existing, ManagementSystem newSystem) {
        // Update logic
        return managementSystemRepository.save(existing);
    }

}
