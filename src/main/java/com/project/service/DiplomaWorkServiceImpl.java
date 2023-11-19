package com.project.service;

import com.project.entity.DiplomaWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.DiplomaWorkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaWorkServiceImpl implements DiplomaWorkService {

    private final DiplomaWorkRepository diplomaWorkRepository;

    @Autowired
    public DiplomaWorkServiceImpl(DiplomaWorkRepository diplomaWorkRepository) {
        this.diplomaWorkRepository = diplomaWorkRepository;
    }

    public List<DiplomaWork> findAll() {
        return diplomaWorkRepository.findAll();
    }

    public Optional<DiplomaWork> findById(Long id) {
        return diplomaWorkRepository.findById(id);
    }

    public DiplomaWork save(DiplomaWork diplomaWork) {
        return diplomaWorkRepository.save(diplomaWork);
    }

    public void deleteById(Long id) {
        diplomaWorkRepository.deleteById(id);
    }

    // Update method can be similar to save, depending on how you handle it in the controller
}
