package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.exception.ConflictException;
import com.pro_servises.pro.exception.NotFoundException;
import com.pro_servises.pro.mapper.ArticleMapper;
import com.pro_servises.pro.model.Admin;
import com.pro_servises.pro.model.Article;
import com.pro_servises.pro.model.Product;
import com.pro_servises.pro.model.Provider;
import com.pro_servises.pro.repository.AdminRepository;
import com.pro_servises.pro.repository.ArticleRepository;
import com.pro_servises.pro.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private AdminRepository adminRepository;


    @Override
    public ArticleDto addArticle(ArticleDto articleDto) {

        Article article = articleMapper.mapToArticle(articleDto);
        article.setArticleDate(new Date(System.currentTimeMillis()));

            Article savedArticle = articleRepository.save(article);
            return articleMapper.mapToArticleDto(savedArticle);
        }

        @Override
        public ArticleDto getArticleById (Integer article_id){
            Article article1 = articleRepository.findById(article_id).orElseThrow(
                    () -> new NotFoundException("id " + article_id + " not found"));
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
        public void deleteArticleById (Integer article_id){
            articleRepository.deleteById(article_id);
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
