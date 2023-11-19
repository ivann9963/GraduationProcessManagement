package com.project.service;

import com.project.entity.DiplomaDefense;

import java.util.List;
import java.util.Optional;

public interface DiplomaDefenseService {
    List<DiplomaDefense> findAll();

    Optional<DiplomaDefense> findById(Long id);

    DiplomaDefense save(DiplomaDefense diplomaDefense);

    void deleteById(Long id);
}
