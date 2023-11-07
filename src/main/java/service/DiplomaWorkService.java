package service;

import entity.DiplomaWork;

import java.util.List;
import java.util.Optional;

public interface DiplomaWorkService {
    List<DiplomaWork> findAll();

    Optional<DiplomaWork> findById(Long id);

    DiplomaWork save(DiplomaWork diplomaWork);

    void deleteById(Long id);
}
