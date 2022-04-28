package org.ksga.springboot.springsecuritydemo.service.impl;

import org.ksga.springboot.springsecuritydemo.model.Article;
import org.ksga.springboot.springsecuritydemo.payload.dto.ArticleDto;
import org.ksga.springboot.springsecuritydemo.payload.mapper.ArticleMapper;
import org.ksga.springboot.springsecuritydemo.repository.ArticleRepository;
import org.ksga.springboot.springsecuritydemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleDto insertArticle(ArticleDto articleDto) {

        return null;
    }

    @Override
    public ArticleDto findArticleById(Long id) {
        Article article = articleRepository.findArticleById(id);
        return articleMapper.articleToArticleDto(article);
    }
}
