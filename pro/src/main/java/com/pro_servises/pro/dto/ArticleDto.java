package com.pro_servises.pro.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class ArticleDto {


    private Integer articleId;
    private String articleTitle;
    private String articleContent;
    private String articleAuthor;
    private Date articleDate;
    private String articleType;
    private String articleImage;
}
