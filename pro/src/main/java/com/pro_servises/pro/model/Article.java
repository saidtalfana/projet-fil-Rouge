package com.pro_servises.pro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer articleId;

    @NotBlank(message = "Article title is required")
    @Size(max = 1000, message = "Article title must not exceed 1000 characters")
    @Column(length = 1000)
    private String articleTitle;

    @NotBlank(message = "Article content is required")
    @Lob
    @Column(columnDefinition = "TEXT")
    private String articleContent;

    @NotBlank(message = "Article author is required")
    @Size(max = 1000, message = "Article author must not exceed 1000 characters")
    @Column(length = 1000)
    private String articleAuthor;

    private Date articleDate;

    @Size(max = 1000, message = "Article type must not exceed 1000 characters")
    @Column(length = 1000)
    private String articleType;

    @Lob
    @Column(length = 1000000)
    private byte[] articleImage;
}
