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
    public ArticleDto addArticle(ArticleDto articleDto, Integer admin_id) {
        Article article = articleMapper.mapToArticle(articleDto);
        Admin admin = adminRepository.findById(admin_id).orElseThrow(
                () -> new NotFoundException("id " + admin_id + " not found"));



            article.setAdmin(admin);
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
        public List<ArticleDto> getAllArticleByAdminId (Integer admin_id) {
            List<Article> articles = articleRepository.findAllArticleByAdmin(admin_id);
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
            Article existingArticle = articleRepository.findById(articleDto.getArticle_id()).orElseThrow(
                    () -> new NotFoundException("id " + articleDto.getArticle_id() + " not found"));
            existingArticle.setArticleTitle(articleDto.getArticle_title());
            existingArticle.setArticleContent(articleDto.getArticle_content());
            existingArticle.setArticleAuthor(articleDto.getArticle_author());
            existingArticle.setArticleDate(articleDto.getArticle_date());
            existingArticle.setArticleImage(articleDto.getArticle_image());
            existingArticle.setArticleType(articleDto.getArticle_type());
            Article updatedArticle = articleRepository.save(existingArticle);
            return articleMapper.mapToArticleDto(updatedArticle);
        }
    }
