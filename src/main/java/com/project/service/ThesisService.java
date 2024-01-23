package com.project.service;

import com.project.dto.ThesisDto;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;

import java.util.List;
import java.util.Optional;

public interface ThesisService {
    Thesis save(Thesis thesis);

    ThesisDto uploadThesis(ThesisDto thesisDto) throws Exception;

    void processThesis(ThesisReview thesisReview) throws Exception;

    List<Thesis> getAllTheses();

    List<Thesis> getThesesById(Long studentId);

    void assignStudentToThesis(Long studentId, Long thesisId) ;

    void assignTeacherToThesis(Long teacherId, Long thesisId) ;

    Optional<Thesis> findById(Long thesisId);
}
