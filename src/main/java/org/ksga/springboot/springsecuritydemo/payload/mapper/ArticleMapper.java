package org.ksga.springboot.springsecuritydemo.payload.mapper;

import org.ksga.springboot.springsecuritydemo.model.Article;
import org.ksga.springboot.springsecuritydemo.payload.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "articleDto.createdAt()", expression = "java(dateToString(article.getCreatedAt()))")
    ArticleDto articleToArticleDto(Article article);

    Article articleDtoToArticle(ArticleDto articleDto);

    default String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/yyyy");
        return formatter.format(date);
    }

}
