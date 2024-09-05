package com.pro_servises.pro.repository;

import com.pro_servises.pro.model.Article;
import com.pro_servises.pro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Product findByName(String title);
}
