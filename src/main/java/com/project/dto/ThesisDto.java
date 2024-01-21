package com.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ThesisDto {
    private Long id;
    private String title;
    private String objective;
    private String tasks;
    private String technologies;
    private Long studentId;
    private Long teacherId;
    private Date submissionDate;
}
