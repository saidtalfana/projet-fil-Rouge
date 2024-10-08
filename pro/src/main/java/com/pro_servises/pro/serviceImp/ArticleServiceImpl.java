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

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleRepository articleRepository) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }


    @Override
    public ArticleDto addArticle(ArticleDto articleDto ,byte[] imageBytes) {

        Article article = articleMapper.mapToArticle(articleDto);
        article.setArticleDate(new Date(System.currentTimeMillis()));
        article.setArticleImage(imageBytes);
            Article savedArticle = articleRepository.save(article);
            return articleMapper.mapToArticleDto(savedArticle);
        }

        @Override
        public ArticleDto getArticleById (Integer articleId){
            Article article1 = articleRepository.findById(articleId).orElseThrow(
                    () -> new NotFoundException("id " + articleId + " not found"));
            return articleMapper.mapToArticleDto(article1);
        }


        @Override
        public List<ArticleDto> getAllArticle () {
            List<Article> articles = articleRepository.findAll();
            return articles.stream()
                    .map(articleMapper::mapToArticleDto)
                    .collect(Collectors.toList());
        }

        @Override
        public void deleteArticleById (Integer articleId){
            articleRepository.deleteById(articleId);
        }


        @Override
        public ArticleDto updateArticle (ArticleDto articleDto){
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
