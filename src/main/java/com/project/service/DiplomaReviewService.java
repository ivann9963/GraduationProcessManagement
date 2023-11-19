package com.project.service;

import com.project.entity.DiplomaReview;

import java.util.List;
import java.util.Optional;

public interface DiplomaReviewService {
    List<DiplomaReview> findAll();

    Optional<DiplomaReview> findById(Long id);

    DiplomaReview save(DiplomaReview diplomaReview);

    void deleteById(Long id);
}

