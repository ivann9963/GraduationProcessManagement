package com.project.service;

import com.project.entity.ThesisReview;
import com.project.repository.ThesisReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ThesisReviewServiceImpl implements ThesisReviewService {
    private final ThesisReviewRepository thesisReviewRepository;

    @Autowired
    public ThesisReviewServiceImpl(ThesisReviewRepository thesisReviewRepository) {
        this.thesisReviewRepository = thesisReviewRepository;
    }

    @Override
    public List<ThesisReview> findAll() {
        return thesisReviewRepository.findAll();
    }

    @Override
    public Optional<ThesisReview> findById(Long id) {
        return thesisReviewRepository.findById(id);
    }

    @Override
    public ThesisReview save(ThesisReview thesisReview) {
        // Additional logic can be added here if needed
        return thesisReviewRepository.save(thesisReview);
    }

    @Override
    public void deleteById(Long id) {
        thesisReviewRepository.deleteById(id);
    }
}
