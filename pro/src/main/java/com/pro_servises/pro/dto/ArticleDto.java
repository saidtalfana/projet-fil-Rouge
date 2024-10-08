package com.pro_servises.pro.dto;


import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;


@Data
@Builder
public class ArticleDto {


    private Integer articleId;
    private String articleTitle;
    private String articleContent;
    private String articleAuthor;
    private Date articleDate;
    private String articleType;
    @Transient
    private byte[] articleImage;
}
