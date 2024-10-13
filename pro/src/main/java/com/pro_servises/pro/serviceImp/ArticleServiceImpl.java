package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ArticleMapper;
import com.pro_servises.pro.model.Article;
import com.pro_servises.pro.repository.ArticleRepository;
import com.pro_servises.pro.service.ArticleService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ArticleService} interface for managing articles.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    /**
     * Constructs an {@link ArticleServiceImpl} with the specified articleMapper and articleRepository.
     *
     * @param articleMapper the mapper for converting between ArticleDto and Article
     * @param articleRepository the repository for accessing Article entities
     */
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleRepository articleRepository) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }

    /**
     * Adds a new article.
     *
     * @param articleDto the data transfer object containing article details
     * @param imageBytes the byte array of the article image
     * @return the saved article as an {@link ArticleDto}
     */
    @Override
    public ArticleDto addArticle(ArticleDto articleDto, byte[] imageBytes) {
        Article article = articleMapper.mapToArticle(articleDto);
        article.setArticleDate(new Date(System.currentTimeMillis()));
        article.setArticleImage(imageBytes);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.mapToArticleDto(savedArticle);
    }

    /**
     * Retrieves an article by its ID.
     *
     * @param articleId the ID of the article to retrieve
     * @return the found article as an {@link ArticleDto}
     * @throws NotFoundException if no article is found with the given ID
     */
    @Override
    public ArticleDto getArticleById(Integer articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new NotFoundException("id " + articleId + " not found"));
        return articleMapper.mapToArticleDto(article);
    }

    /**
     * Retrieves all articles.
     *
     * @return a list of all articles as {@link ArticleDto}
     */
    @Override
    public List<ArticleDto> getAllArticle() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::mapToArticleDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes an article by its ID.
     *
     * @param articleId the ID of the article to delete
     */
    @Override
    public void deleteArticleById(Integer articleId) {
        articleRepository.deleteById(articleId);
    }

    /**
     * Updates an existing article.
     *
     * @param articleDto the data transfer object containing updated article details
     * @return the updated article as an {@link ArticleDto}
     * @throws NotFoundException if no article is found with the given ID
     */
    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Article existingArticle = articleRepository.findById(articleDto.getArticleId()).orElseThrow(
                () -> new NotFoundException("id " + articleDto.getArticleId() + " not found"));
        existingArticle.setArticleTitle(articleDto.getArticleTitle());
        existingArticle.setArticleContent(articleDto.getArticleContent());
        existingArticle.setArticleAuthor(articleDto.getArticleAuthor());
        existingArticle.setArticleDate(articleDto.getArticleDate());
        existingArticle.setArticleImage(articleDto.getArticleImage());
        existingArticle.setArticleType(articleDto.getArticleType());
        Article updatedArticle = articleRepository.save(existingArticle);
        return articleMapper.mapToArticleDto(updatedArticle);
    }
}
