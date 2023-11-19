package com.project.service;

import com.project.entity.DiplomaReview;
import com.project.repository.DiplomaReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomaReviewServiceImpl implements DiplomaReviewService {
    private final DiplomaReviewRepository diplomaReviewRepository;

    @Autowired
    public DiplomaReviewServiceImpl(DiplomaReviewRepository diplomaReviewRepository) {
        this.diplomaReviewRepository = diplomaReviewRepository;
    }


    public List<DiplomaReview> findAll() {
        return diplomaReviewRepository.findAll();
    }

    public Optional<DiplomaReview> findById(Long id) {
        return diplomaReviewRepository.findById(id);
    }

    public DiplomaReview save(DiplomaReview diplomaReview) {
        return diplomaReviewRepository.save(diplomaReview);
    }

    public void deleteById(Long id) {
        diplomaReviewRepository.deleteById(id);
    }
}
