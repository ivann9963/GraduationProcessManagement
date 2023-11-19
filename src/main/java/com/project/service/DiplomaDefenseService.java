package com.project.service;

import com.project.entity.DiplomaDefense;

import java.util.List;
import java.util.Optional;

public interface DiplomaDefenseService {
    DiplomaDefense createDefense(DiplomaDefense defense);
    Optional<DiplomaDefense> getDefenseById(Long id);
    List<DiplomaDefense> getAllDefenses();
    DiplomaDefense updateDefense(Long id, DiplomaDefense defenseDetails);
    void deleteDefense(Long id);
}
