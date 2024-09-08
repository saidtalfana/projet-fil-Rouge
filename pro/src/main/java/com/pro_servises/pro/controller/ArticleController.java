package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.service.ArticleService;
import com.pro_servises.pro.serviceImp.ArticleServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;


    @PostMapping("/add_article/{admin_id}")
    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto articleDto,
                                                 @PathVariable("admin_id") Integer admin_id) {
        ArticleDto createdArticle=  articleServiceImpl.addArticle(articleDto,admin_id);
        return ResponseEntity.ok(createdArticle);
    }



    @GetMapping("/get_article/{article_id}")
    public ArticleDto getArticle(@PathVariable Integer article_id) {
        return articleServiceImpl.getArticleById(article_id);
    }



    @GetMapping("/get_articles_by_admin_id/{admin_id}")
    public List<ArticleDto> getArticles(@PathVariable  Integer admin_id) {
        return articleServiceImpl.getAllArticleByAdminId(admin_id);
    }



    @DeleteMapping("/delete_article/{article_id}")
    public void deleteArticle(@PathVariable Integer article_id) {
        articleServiceImpl.deleteArticleById(article_id);
    }


    @PutMapping("/update_article")
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleServiceImpl.updateArticle(articleDto);
    }
}
