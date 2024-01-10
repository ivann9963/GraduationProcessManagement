package com.project.service;

import com.project.entity.Thesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.ThesisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ThesisServiceImpl implements ThesisService {

    private final ThesisRepository thesisRepository;

    @Autowired
    public ThesisServiceImpl(ThesisRepository thesisRepository) {
        this.thesisRepository = thesisRepository;
    }

    public List<Thesis> findAll() {
        return thesisRepository.findAll();
    }

    public Optional<Thesis> findById(Long id) {
        return thesisRepository.findById(id);
    }

    public Thesis save(Thesis thesis) {
        return thesisRepository.save(thesis);
    }

    public void deleteById(Long id) {
        thesisRepository.deleteById(id);
    }

}
