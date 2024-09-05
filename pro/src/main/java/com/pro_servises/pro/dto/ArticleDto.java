package com.pro_servises.pro.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class ArticleDto {


    private Integer article_id;
    private String article_title;
    private String article_content;
    private String article_author;
    private Date article_date;
    private String article_type;
    private String article_image;
}
