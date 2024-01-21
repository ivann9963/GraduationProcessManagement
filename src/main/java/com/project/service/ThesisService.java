package com.project.service;

import com.project.dto.ThesisDto;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;

import java.util.List;
public interface ThesisService {
    Thesis save(Thesis thesis);

    ThesisDto uploadThesis(ThesisDto thesisDto);

    void processThesis(Long thesisId, ThesisReview thesisReview) throws Exception;

    List<Thesis> getAllTheses();

}
