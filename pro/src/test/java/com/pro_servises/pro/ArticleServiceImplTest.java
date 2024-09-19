//package com.pro_servises.pro;
//
//import com.pro_servises.pro.dto.ArticleDto;
//import com.pro_servises.pro.exception.NotFoundException;
//import com.pro_servises.pro.mapper.ArticleMapper;
//import com.pro_servises.pro.model.Article;
//import com.pro_servises.pro.repository.ArticleRepository;
//import com.pro_servises.pro.serviceImp.ArticleServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Date;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class ArticleServiceImplTest {
//
//    @InjectMocks
//    private ArticleServiceImpl articleService;
//
//    @Mock
//    private ArticleRepository articleRepository;
//
//    @Mock
//    private ArticleMapper articleMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddArticle() {
//        // Given
//        ArticleDto articleDto = new ArticleDto();
//        Article article = new Article();
//        Article savedArticle = new Article();
//        ArticleDto savedArticleDto = new ArticleDto();
//
//        // Configure mocks
//        when(articleMapper.mapToArticle(articleDto)).thenReturn(article);
//        when(articleRepository.save(article)).thenReturn(savedArticle);
//        when(articleMapper.mapToArticleDto(savedArticle)).thenReturn(savedArticleDto);
//
//        // When
//        ArticleDto result = articleService.addArticle(articleDto);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(savedArticleDto, result);
//        verify(articleMapper, times(1)).mapToArticle(articleDto);
//        verify(articleRepository, times(1)).save(article);
//        verify(articleMapper, times(1)).mapToArticleDto(savedArticle);
//    }
//
//
//    @Test
//    void testGetArticleById() {
//        Integer articleId = 1;
//        Article article = new Article();
//        ArticleDto articleDto = new ArticleDto();
//
//        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));
//        when(articleMapper.mapToArticleDto(article)).thenReturn(articleDto);
//
//        ArticleDto result = articleService.getArticleById(articleId);
//
//        assertNotNull(result);
//        verify(articleRepository, times(1)).findById(articleId);
//    }
//
//    @Test
//    void testGetArticleByIdNotFound() {
//        Integer articleId = 1;
//
//        when(articleRepository.findById(articleId)).thenReturn(Optional.empty());
//
//        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
//            articleService.getArticleById(articleId);
//        });
//
//        assertEquals("id 1 not found", thrown.getMessage());
//    }
//
////    @Test
////    void testUpdateArticle() {
////        ArticleDto articleDto = new ArticleDto();
////        Article existingArticle = new Article();
////        Article updatedArticle = new Article();
////        updatedArticle.setArticleId(1);
////
////        when(articleRepository.findById(articleDto.getArticleId())).thenReturn(Optional.of(existingArticle));
////        when(articleMapper.mapToArticle(articleDto)).thenReturn(existingArticle);
////        when(articleRepository.save(existingArticle)).thenReturn(updatedArticle);
////        when(articleMapper.mapToArticleDto(updatedArticle)).thenReturn(articleDto);
////
////        ArticleDto result = articleService.updateArticle(articleDto);
////
////        assertNotNull(result);
////        assertEquals(1, result.getArticleId());
////        verify(articleRepository, times(1)).save(existingArticle);
////    }
//
//    @Test
//    public void testUpdateArticle() {
//        // Given
//        Integer articleId = 1;
//        ArticleDto articleDto = new ArticleDto();
//        articleDto.setArticleId(articleId);
//        articleDto.setArticleTitle("Updated Title");
//        articleDto.setArticleContent("Updated Content");
//        articleDto.setArticleAuthor("Updated Author");
//        articleDto.setArticleDate(new Date(System.currentTimeMillis()));
//        articleDto.setArticleImage("updated_image.png");
//        articleDto.setArticleType("Updated Type");
//
//        Article existingArticle = new Article();
//        existingArticle.setArticleId(articleId);
//        existingArticle.setArticleTitle("Original Title");
//        existingArticle.setArticleContent("Original Content");
//        existingArticle.setArticleAuthor("Original Author");
//        existingArticle.setArticleDate(new Date(System.currentTimeMillis() - 1000)); // Some past date
//        existingArticle.setArticleImage("original_image.png");
//        existingArticle.setArticleType("Original Type");
//
//        Article updatedArticle = new Article();
//        updatedArticle.setArticleId(articleId);
//        updatedArticle.setArticleTitle(articleDto.getArticleTitle());
//        updatedArticle.setArticleContent(articleDto.getArticleContent());
//        updatedArticle.setArticleAuthor(articleDto.getArticleAuthor());
//        updatedArticle.setArticleDate(articleDto.getArticleDate());
//        updatedArticle.setArticleImage(articleDto.getArticleImage());
//        updatedArticle.setArticleType(articleDto.getArticleType());
//
//        // Mock interactions
//        when(articleRepository.findById(articleId)).thenReturn(java.util.Optional.of(existingArticle));
//        when(articleRepository.save(existingArticle)).thenReturn(updatedArticle);
//        when(articleMapper.mapToArticleDto(updatedArticle)).thenReturn(articleDto);
//
//        // When
//        ArticleDto result = articleService.updateArticle(articleDto);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(articleId, result.getArticleId());
//        assertEquals(articleDto.getArticleTitle(), result.getArticleTitle());
//        assertEquals(articleDto.getArticleContent(), result.getArticleContent());
//        assertEquals(articleDto.getArticleAuthor(), result.getArticleAuthor());
//        assertEquals(articleDto.getArticleDate(), result.getArticleDate());
//        assertEquals(articleDto.getArticleImage(), result.getArticleImage());
//        assertEquals(articleDto.getArticleType(), result.getArticleType());
//
//        // Verify interactions
//        verify(articleRepository, times(1)).findById(articleId);
//        verify(articleRepository, times(1)).save(existingArticle);
//        verify(articleMapper, times(1)).mapToArticleDto(updatedArticle);
//    }
//
//    @Test
//    public void testUpdateArticle_NotFound() {
//        // Given
//        ArticleDto articleDto = new ArticleDto();
//        articleDto.setArticleId(1);
//
//        // Mock interactions
//        when(articleRepository.findById(articleDto.getArticleId())).thenReturn(java.util.Optional.empty());
//
//        // When / Then
//        NotFoundException thrown = assertThrows(
//                NotFoundException.class,
//                () -> articleService.updateArticle(articleDto),
//                "Expected updateArticle to throw, but it didn't"
//        );
//
//        assertTrue(thrown.getMessage().contains("id 1 not found"));
//
//        // Verify interactions
//        verify(articleRepository, times(1)).findById(articleDto.getArticleId());
//        verify(articleRepository, never()).save(any(Article.class));
//        verify(articleMapper, never()).mapToArticleDto(any(Article.class));
//    }
//
//
//
//
//
//    @Test
//    void testDeleteArticleById() {
//        Integer articleId = 1;
//
//        doNothing().when(articleRepository).deleteById(articleId);
//
//        articleService.deleteArticleById(articleId);
//
//        verify(articleRepository, times(1)).deleteById(articleId);
//    }
//}
