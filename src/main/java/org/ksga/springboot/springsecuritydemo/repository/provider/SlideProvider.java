package org.ksga.springboot.springsecuritydemo.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class SlideProvider {
    public String findAll(){
        return new SQL(){{
            SELECT("*");
            FROM("ha_slide");
//            INNER_JOIN("ha_course on ha_slide.course_id = ha_course.id");
//            INNER_JOIN("ha_course On ha_slide.id = ha_course.course_id");

        }}.toString();
    }
}
