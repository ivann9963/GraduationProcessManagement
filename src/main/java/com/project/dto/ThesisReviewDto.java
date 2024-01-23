package com.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThesisReviewDto {
    private Long id;
    private Long thesisId;
    private String text;
    private Boolean conclusion;
}
