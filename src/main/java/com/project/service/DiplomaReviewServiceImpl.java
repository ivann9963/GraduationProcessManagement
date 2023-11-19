package com.project.service;

import com.project.entity.DiplomaReview;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaReviewServiceImpl implements DiplomaReviewService {
    @Override
    public DiplomaReview createReview(DiplomaReview review) {
        return null;
    }

    @Override
    public Optional<DiplomaReview> getReviewById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DiplomaReview> getAllReviews() {
        return null;
    }

    @Override
    public DiplomaReview updateReview(Long id, DiplomaReview reviewDetails) {
        return null;
    }

    @Override
    public void deleteReview(Long id) {

    }
}
