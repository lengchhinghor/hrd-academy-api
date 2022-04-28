package org.ksga.springboot.springsecuritydemo.model.slide;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.ksga.springboot.springsecuritydemo.model.course.Course;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Slide {
    private Long id;
    private String name;
    private String url;
    private Integer course_id;
    private Course course;
}
