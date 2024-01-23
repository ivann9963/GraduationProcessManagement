package com.project.dto;

import com.project.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    private Long id;
    private String name;
    private Teacher.Position position;
    private Role role;
}
