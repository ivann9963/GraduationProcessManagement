package com.project.service;

import com.project.entity.Thesis;
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


    public List<com.project.entity.ThesisReview> findAll() {
        return thesisReviewRepository.findAll();
    }

    public Optional<com.project.entity.ThesisReview> findById(Long id) {
        return thesisReviewRepository.findById(id);
    }

    public com.project.entity.ThesisReview save(com.project.entity.ThesisReview thesisReview) {
        return this.thesisReviewRepository.save(thesisReview);
    }

    public void deleteById(Long id) {
        thesisReviewRepository.deleteById(id);
    }

}
