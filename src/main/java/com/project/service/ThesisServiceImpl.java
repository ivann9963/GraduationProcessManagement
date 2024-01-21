package com.project.service;

import com.project.dto.ThesisDto;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;
import com.project.repository.ThesisRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    ModelMapper modelMapper;
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
    public void processThesis(Long thesisId, ThesisReview thesisReview) throws Exception {
        Thesis existingThesis = thesisRepository.findById(thesisId).get();

        if(existingThesis.getThesisReview() != null){
            throw new Exception("ThesisReview already exists!");
        }

        existingThesis.setThesisReview(thesisReview);
    }

    @Override
    public List<Thesis> getAllTheses() {
        return thesisRepository.findAll();
    }

    @Override
    public ThesisDto uploadThesis(ThesisDto thesisUploadDto) {
        Thesis thesis = modelMapper.map(thesisUploadDto, Thesis.class);
        Thesis savedThesis = thesisRepository.save(thesis);
        return modelMapper.map(savedThesis, ThesisDto.class);
    }

}
