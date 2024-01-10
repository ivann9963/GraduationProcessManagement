package com.project.service;

import com.project.entity.Thesis;

import java.util.List;
import java.util.Optional;

public interface ThesisService {
    List<Thesis> findAll();

    Optional<Thesis> findById(Long id);

    Thesis save(Thesis thesis);

    void deleteById(Long id);
}
