package org.ksga.springboot.springsecuritydemo.service;

import org.ksga.springboot.springsecuritydemo.payload.dto.ArticleDto;

public interface ArticleService {
    ArticleDto insertArticle(ArticleDto articleDto);

    ArticleDto findArticleById(Long id);
}
