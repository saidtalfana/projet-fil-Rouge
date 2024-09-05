package com.pro_servises.pro.mapper;


import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.model.Article;
import com.pro_servises.pro.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    ArticleDto mapToArticleDto(Article article);

    Article mapToArticle(ArticleDto articleDto);
}
