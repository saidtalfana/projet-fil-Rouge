package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.ArticleDto;

import java.util.List;

public interface ArticleService {


    ArticleDto addArticle(ArticleDto articleDto, Integer admin_id);

    ArticleDto getArticleById(Integer article_id);

    List<ArticleDto> getAllArticle();

    void deleteArticleById(Integer article_id);

    ArticleDto updateArticle(ArticleDto articleDto);

}
