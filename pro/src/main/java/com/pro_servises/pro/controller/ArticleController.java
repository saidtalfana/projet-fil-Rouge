package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.service.ArticleService;
import com.pro_servises.pro.serviceImp.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;


    @PostMapping("/add_article")
    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto articleDto, @RequestParam Integer admin_id) {
        ArticleDto createdArticle=  articleServiceImpl.addArticle(articleDto,admin_id);
        return ResponseEntity.ok(createdArticle);
    }



    @GetMapping("/get_article/{id}")
    public ArticleDto getArticle(@PathVariable Integer article_id) {
        return articleServiceImpl.getArticleById(article_id);
    }



    @GetMapping("/get_articles")
    public List<ArticleDto> getArticles() {
        return articleServiceImpl.getAllArticle();
    }



    @DeleteMapping("/delete_article/{id}")
    public void deleteArticle(@PathVariable Integer article_id) {
        articleServiceImpl.deleteArticleById(article_id);
    }


    @PutMapping("/update_article")
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleServiceImpl.updateArticle(articleDto);
    }
}
