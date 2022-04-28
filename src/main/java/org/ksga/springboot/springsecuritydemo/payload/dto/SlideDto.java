package org.ksga.springboot.springsecuritydemo.payload.dto;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.ksga.springboot.springsecuritydemo.model.course.Course;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SlideDto {
    private Long id;
    private String name;
    private String url;

}
