package com.pro_servises.pro;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ArticleMapper;
import com.pro_servises.pro.model.Article;
import com.pro_servises.pro.repository.ArticleRepository;
import com.pro_servises.pro.serviceImp.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleMapper articleMapper;

    private ArticleDto articleDto;
    private Article article;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        articleDto = ArticleDto.builder()
                .articleId(1)
                .articleTitle("Title")
                .articleContent("Content")
                .articleAuthor("Author")
                .articleDate(new Date(System.currentTimeMillis()))
                .articleType("Type")
                .build();

        article = new Article();
        article.setArticleId(1);
        article.setArticleTitle("Title");
        article.setArticleContent("Content");
        article.setArticleAuthor("Author");
        article.setArticleDate(new Date(System.currentTimeMillis()));
        article.setArticleType("Type");
    }

    @Test
    void addArticle_shouldSaveArticleAndReturnDto() {
        when(articleMapper.mapToArticle(articleDto)).thenReturn(article);
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        when(articleMapper.mapToArticleDto(any(Article.class))).thenReturn(articleDto);

        ArticleDto result = articleService.addArticle(articleDto, new byte[0]);

        assertNotNull(result);
        assertEquals(articleDto.getArticleId(), result.getArticleId());
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    void getArticleById_shouldReturnArticleDto_whenFound() {
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));
        when(articleMapper.mapToArticleDto(article)).thenReturn(articleDto);

        ArticleDto result = articleService.getArticleById(1);

        assertNotNull(result);
        assertEquals(articleDto.getArticleId(), result.getArticleId());
    }

    @Test
    void getArticleById_shouldThrowNotFoundException_whenNotFound() {
        when(articleRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> articleService.getArticleById(1));
    }

    @Test
    void getAllArticle_shouldReturnListOfArticleDtos() {
        when(articleRepository.findAll()).thenReturn(Arrays.asList(article));
        when(articleMapper.mapToArticleDto(article)).thenReturn(articleDto);

        var result = articleService.getAllArticle();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void deleteArticleById_shouldCallDeleteOnRepository() {
        doNothing().when(articleRepository).deleteById(1);

        articleService.deleteArticleById(1);

        verify(articleRepository, times(1)).deleteById(1);
    }

    @Test
    void updateArticle_shouldUpdateAndReturnDto() {
        when(articleRepository.findById(articleDto.getArticleId())).thenReturn(Optional.of(article));
        when(articleMapper.mapToArticleDto(any(Article.class))).thenReturn(articleDto);
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        ArticleDto result = articleService.updateArticle(articleDto);

        assertNotNull(result);
        assertEquals(articleDto.getArticleId(), result.getArticleId());
    }

    @Test
    void updateArticle_shouldThrowNotFoundException_whenNotFound() {
        when(articleRepository.findById(articleDto.getArticleId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> articleService.updateArticle(articleDto));
    }
}
