package com.project.service;

import com.project.entity.DiplomaAcceptance;
import com.project.repository.DiplomaAcceptanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaAcceptanceServiceImpl implements DiplomaAcceptanceService{

    private final DiplomaAcceptanceRepository diplomaAcceptanceRepository;

    @Autowired
    public DiplomaAcceptanceServiceImpl(DiplomaAcceptanceRepository diplomaAcceptanceRepository) {
        this.diplomaAcceptanceRepository = diplomaAcceptanceRepository;
    }

    public List<DiplomaAcceptance> findAll() {
        return diplomaAcceptanceRepository.findAll();
    }

    public Optional<DiplomaAcceptance> findById(Long id) {
        return diplomaAcceptanceRepository.findById(id);
    }

    public DiplomaAcceptance save(DiplomaAcceptance diplomaAcceptance) {
        return diplomaAcceptanceRepository.save(diplomaAcceptance);
    }

    public void deleteById(Long id) {
        diplomaAcceptanceRepository.deleteById(id);
    }
}
