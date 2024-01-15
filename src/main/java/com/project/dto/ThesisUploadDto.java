package com.project.dto;

import com.project.entity.ThesisReview;

import java.util.Date;

public class ThesisUploadDto {
    private String title;
    private String objective;
    private String tasks;
    private String technologies;
    private Long studentId; // Assuming you're passing the ID of the student
    private Date submissionDate;
}
