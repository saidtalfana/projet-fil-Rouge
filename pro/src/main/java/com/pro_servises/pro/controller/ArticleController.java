package com.pro_servises.pro.controller;

import com.pro_servises.pro.dto.ArticleDto;
import com.pro_servises.pro.serviceImp.ArticleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleServiceImpl articleServiceImpl;

    public ArticleController(ArticleServiceImpl articleServiceImpl) {
        this.articleServiceImpl = articleServiceImpl;
    }

    /**
     * Add a new article with an image
     *
     * @param articleTitle   the title of the article
     * @param articleContent the content of the article
     * @param articleAuthor  the author of the article
     * @param articleType    the type/category of the article
     * @param articleImage   the image file associated with the article
     * @return the created ArticleDto object
     * @throws IOException if an error occurs during image processing
     */
    @PostMapping("/add_article")
    public ResponseEntity<ArticleDto> addArticle(@RequestParam String articleTitle,
                                                 @RequestParam String articleContent,
                                                 @RequestParam String articleAuthor,
                                                 @RequestParam String articleType,
                                                 @RequestPart("articleImage") MultipartFile articleImage) throws IOException {
        byte[] imageBytes = articleImage.getBytes();
        ArticleDto articleDto = ArticleDto.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent)
                .articleAuthor(articleAuthor)
                .articleType(articleType)
                .build();

        ArticleDto createdArticle = articleServiceImpl.addArticle(articleDto, imageBytes);
        return ResponseEntity.ok(createdArticle);
    }

    /**
     * Get an article by its ID
     *
     * @param articleId the ID of the article to retrieve
     * @return the ArticleDto object containing article details
     */
    @GetMapping("/get_article/{articleId}")
    public ArticleDto getArticle(@PathVariable Integer articleId) {
        return articleServiceImpl.getArticleById(articleId);
    }

    /**
     * Get a list of all articles
     *
     * @return a list of ArticleDto objects containing article details
     */
    @GetMapping("/get_articles")
    public List<ArticleDto> getArticles() {
        return articleServiceImpl.getAllArticle();
    }

    /**
     * Delete an article by its ID
     *
     * @param articleId the ID of the article to delete
     */
    @DeleteMapping("/delete_article/{articleId}")
    public void deleteArticle(@PathVariable Integer articleId) {
        articleServiceImpl.deleteArticleById(articleId);
    }

    /**
     * Update an existing article
     *
     * @param articleDto the updated article data
     * @return the updated ArticleDto object
     */
    @PutMapping("/update_article")
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleServiceImpl.updateArticle(articleDto);
    }
}

