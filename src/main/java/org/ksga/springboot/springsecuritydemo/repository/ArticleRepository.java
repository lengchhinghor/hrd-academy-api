package org.ksga.springboot.springsecuritydemo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ksga.springboot.springsecuritydemo.model.Article;

@Mapper
public interface ArticleRepository {

    @Select("SELECT * FROM articles WHERE id = #{id}")
    Article findArticleById(@Param("id") Long id);

}
