package com.project.service;

import com.project.entity.DiplomaReview;

import java.util.List;
import java.util.Optional;

public interface DiplomaReviewService {
    DiplomaReview createReview(DiplomaReview review);
    Optional<DiplomaReview> getReviewById(Long id);
    List<DiplomaReview> getAllReviews();
    DiplomaReview updateReview(Long id, DiplomaReview reviewDetails);
    void deleteReview(Long id);
}
