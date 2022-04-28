package org.ksga.springboot.springsecuritydemo.repository;

import org.apache.ibatis.annotations.*;
import org.ksga.springboot.springsecuritydemo.model.course.Course;
import org.ksga.springboot.springsecuritydemo.model.slide.Slide;
import org.ksga.springboot.springsecuritydemo.repository.provider.SlideProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlideRepository {
//    @SelectProvider(type = SlideProvider.class, method = "findAll")
//    @Results(id = "slideMapping",
//            value = {
//                    @Result(column = "course_id",property = "course.id"),
//            })
    @Select("select * from ha_slide")
    public List<Slide> findAll();
}
