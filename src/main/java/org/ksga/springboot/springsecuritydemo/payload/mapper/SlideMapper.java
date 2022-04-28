package org.ksga.springboot.springsecuritydemo.payload.mapper;

import org.ksga.springboot.springsecuritydemo.model.slide.Slide;
import org.ksga.springboot.springsecuritydemo.payload.dto.SlideDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface SlideMapper {

    @Mapping(target = "slideDto.createdAt()", expression = "java(dateToString(article.getCreatedAt()))")
    SlideDto slideToSlideDto(Slide slide);

    Slide slideToSlideDto(SlideDto slideDto);

    default String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/yyyy");
        return formatter.format(date);
    }

}
