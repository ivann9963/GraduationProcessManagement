package com.project.service;

import com.project.dto.ThesisDto;
import com.project.entity.Student;
import com.project.entity.Teacher;
import com.project.entity.Thesis;
import com.project.entity.ThesisReview;
import com.project.repository.StudentRepository;
import com.project.repository.TeacherRepository;
import com.project.repository.ThesisRepository;
import com.project.repository.ThesisReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    ModelMapper modelMapper;
    private final ThesisRepository thesisRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final ThesisReviewRepository thesisReviewRepository;

    @Autowired
    public ThesisServiceImpl(ThesisRepository thesisRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ThesisReviewRepository thesisReviewRepository) {
        this.thesisRepository = thesisRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.thesisReviewRepository = thesisReviewRepository;
    }

    @Override
    public Thesis save(Thesis thesis) {
        return thesisRepository.save(thesis);
    }

    @Override
    public void processThesis(ThesisReview thesisReview) throws Exception {
        Thesis existingThesis = thesisRepository.findById(thesisReview.getThesis().getId()).get();

        if(existingThesis.getThesisReview() != null){
            throw new Exception("ThesisReview already exists!");
        }

        ThesisReview savedReview = thesisReviewRepository.save(thesisReview);
        existingThesis.setThesisReview(thesisReview);

        thesisRepository.save(existingThesis);
    }

    @Override
    public List<Thesis> getAllTheses() {
        return thesisRepository.findAll();
    }

    @Override
    public List<Thesis> getThesesById(Long studentId) {
        return thesisRepository.findAll().stream().filter(t -> t.getStudent().getId().equals(studentId)).toList();
    }

    @Override
    public ThesisDto uploadThesis(ThesisDto thesisUploadDto) {
        Thesis thesis = modelMapper.map(thesisUploadDto, Thesis.class);
        Thesis savedThesis = thesisRepository.save(thesis);
        assignStudentToThesis(thesisUploadDto.getStudentId(), savedThesis.getId());
        return modelMapper.map(savedThesis, ThesisDto.class);
    }

    @Override
    public void assignStudentToThesis(Long studentId, Long thesisId){
        Thesis thesis = thesisRepository.findById(thesisId).get();
        Student student = studentRepository.findById(studentId).get();
        thesis.setStudent(student);
        thesisRepository.save(thesis);
    }

    @Override
    public void assignTeacherToThesis(Long teacherId, Long thesisId) {
        Thesis thesis = thesisRepository.findById(thesisId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        thesis.setTeacher(teacher);

    }

    @Override
    public Optional<Thesis> findById(Long thesisId) {
        return thesisRepository.findById(thesisId);
    }
}
