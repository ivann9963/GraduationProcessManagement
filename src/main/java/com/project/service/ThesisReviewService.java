package com.project.service;

import com.project.entity.Thesis;
import com.project.entity.ThesisReview;

import java.util.List;
import java.util.Optional;

public interface ThesisReviewService {
    List<ThesisReview> findAll();

    Optional<ThesisReview> findById(Long id);

    ThesisReview save(ThesisReview thesisReview);

    void deleteById(Long id);

}

