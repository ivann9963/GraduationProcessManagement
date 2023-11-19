package com.project.service;

import com.project.entity.DiplomaAcceptance;

import java.util.List;
import java.util.Optional;

public interface DiplomaAcceptanceService {
    List<DiplomaAcceptance> findAll();

    Optional<DiplomaAcceptance> findById(Long id);

    DiplomaAcceptance save(DiplomaAcceptance diplomaAcceptance);

    void deleteById(Long id);
}
