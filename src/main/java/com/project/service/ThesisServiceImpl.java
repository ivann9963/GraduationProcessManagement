package com.project.service;

import com.project.entity.Thesis;
import com.project.entity.ThesisReview;
import com.project.repository.ThesisRepository;
import com.project.repository.ThesisReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
public class ThesisServiceImpl implements ThesisService {

    private final ThesisRepository thesisRepository;

    @Autowired
    private ThesisReviewServiceImpl thesisReviewService;


    @Autowired
    public ThesisServiceImpl(ThesisRepository thesisRepository) {
        this.thesisRepository = thesisRepository;
    }

    @Override
    public Thesis save(Thesis thesis) {
        return thesisRepository.save(thesis);
    }

    @Override
    /*** Как ще проверяваме дали текущият user е Студент или преподавател */
    @PreAuthorize("hasRole('TEACHER')")
    public void processThesis(Long thesisId, ThesisReview thesisReview) throws Exception {
        Thesis existingThesis = thesisRepository.findById(thesisId).get();

        if(existingThesis.getThesisReview() != null){
            throw new Exception("ThesisReview already exists!");
        }

        existingThesis.setThesisReview(thesisReview);
    }


    @Override
    @PreAuthorize("hasRole('STUDENT')")
    public Thesis uploadThesis(Thesis thesis) {
        return thesisRepository.save(thesis);

    }

}
