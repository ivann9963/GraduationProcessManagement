package com.project.service;

import com.project.entity.Thesis;
import com.project.entity.ThesisReview;

public interface ThesisService {
    Thesis save(Thesis thesis);

    Thesis uploadThesis(Thesis thesis);

    void processThesis(Long thesisId, ThesisReview thesisReview) throws Exception;

}
