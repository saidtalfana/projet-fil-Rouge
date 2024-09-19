package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.dto.ProductDto;
import com.pro_servises.pro.enums.Category;
import com.pro_servises.pro.enums.ProductStatus;
import com.pro_servises.pro.service.ArticleService;
import com.pro_servises.pro.serviceImp.ArticleServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;

    @PostMapping("/add_article")
    public ResponseEntity<ArticleDto> addArticle(@RequestParam String articleTitle,
                                                 @RequestParam String articleContent,
                                                 @RequestParam String articleAuthor,
                                                 @RequestParam String articleType,
                                                 @RequestPart("articleImage") MultipartFile articleImage) throws IOException

    {
        System.out.printf("zise if image" + articleImage.getSize());

        byte[] imageBytes = articleImage.getBytes();
        ArticleDto articleDto = ArticleDto.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent)
                .articleAuthor(articleAuthor)
                .articleType(articleType)
                .build();




        ArticleDto createdArticle=  articleServiceImpl.addArticle(articleDto,imageBytes);
        return ResponseEntity.ok(createdArticle);
    }



    @GetMapping("/get_article/{article_id}")
    public ArticleDto getArticle(@PathVariable Integer article_id) {
        return articleServiceImpl.getArticleById(article_id);
    }



    @GetMapping("/get_articles")
    public List<ArticleDto> getArticles() {
        return articleServiceImpl.getAllArticle();
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
