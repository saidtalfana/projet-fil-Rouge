package com.pro_servises.pro.mapper;


import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {


    ArticleDto mapToArticleDto(Article article);

    Article mapToArticle(ArticleDto articleDto);
}
