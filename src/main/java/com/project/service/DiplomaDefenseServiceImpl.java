package com.project.service;

import com.project.entity.DiplomaDefense;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaDefenseServiceImpl implements DiplomaDefenseService{
    @Override
    public DiplomaDefense createDefense(DiplomaDefense defense) {
        return null;
    }

    @Override
    public Optional<DiplomaDefense> getDefenseById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DiplomaDefense> getAllDefenses() {
        return null;
    }

    @Override
    public DiplomaDefense updateDefense(Long id, DiplomaDefense defenseDetails) {
        return null;
    }

    @Override
    public void deleteDefense(Long id) {

    }
}
