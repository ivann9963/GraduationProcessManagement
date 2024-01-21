package com.project.utilities;

import com.project.dto.ThesisDto;
import com.project.entity.Thesis;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoUtils {

    private final ModelMapper modelMapper;

    public DtoUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ThesisDto convertToDto(Thesis thesis) {
        return modelMapper.map(thesis, ThesisDto.class);
    }

    public Thesis convertToEntity(ThesisDto thesisDto) {
        return modelMapper.map(thesisDto, Thesis.class);
    }
}
