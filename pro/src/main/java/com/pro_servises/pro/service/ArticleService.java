package com.pro_servises.pro.service;

import com.pro_servises.pro.dto.ArticleDto;
import java.util.List;

public interface ArticleService {


    ArticleDto addArticle(ArticleDto articleDto,byte[] articleImage);

    ArticleDto getArticleById(Integer articleId);

    List<ArticleDto> getAllArticle();

    void deleteArticleById(Integer articleId);

    ArticleDto updateArticle(ArticleDto articleDto);

}
