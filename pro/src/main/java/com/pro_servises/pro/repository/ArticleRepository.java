package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query(value = "SELECT *  FROM Article WHERE admin_id =?1" , nativeQuery = true)
    List<Article> findAllArticleByAdmin(Integer adminId);
}
