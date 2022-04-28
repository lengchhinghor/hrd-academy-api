package org.ksga.springboot.springsecuritydemo.controller.rest;

import org.ksga.springboot.springsecuritydemo.payload.dto.ArticleDto;
import org.ksga.springboot.springsecuritydemo.payload.response.Response;
import org.ksga.springboot.springsecuritydemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Response<ArticleDto> getArticle(@PathVariable Long id) {
        ArticleDto articleDto = articleService.findArticleById(id);
        return Response
                .<ArticleDto>ok()
                .setPayload(articleDto);
    }

}
