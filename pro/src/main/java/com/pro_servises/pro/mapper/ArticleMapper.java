package com.pro_servises.pro.mapper;


import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {


    ArticleDto mapToArticleDto(Article article);

    Article mapToArticle(ArticleDto articleDto);
}
