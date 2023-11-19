package com.project.service;

import com.project.entity.DiplomaDefense;
import com.project.repository.DiplomaDefenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaDefenseServiceImpl implements DiplomaDefenseService{

    private final DiplomaDefenseRepository diplomaDefenseRepository;

    @Autowired
    public DiplomaDefenseServiceImpl(DiplomaDefenseRepository diplomaDefenseRepository) {
        this.diplomaDefenseRepository = diplomaDefenseRepository;
    }

    public List<DiplomaDefense> findAll() {
        return diplomaDefenseRepository.findAll();
    }

    public Optional<DiplomaDefense> findById(Long id) {
        return diplomaDefenseRepository.findById(id);
    }

    public DiplomaDefense save(DiplomaDefense diplomaDefense) {
        return diplomaDefenseRepository.save(diplomaDefense);
    }

    public void deleteById(Long id) {
        diplomaDefenseRepository.deleteById(id);
    }
}
