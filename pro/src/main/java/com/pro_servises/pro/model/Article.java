package com.pro_servises.pro.model;

import jakarta.persistence.*;
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
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer articleId;
    @Column(length = 1000)
    private String articleTitle;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String articleContent;
    @Column(length = 1000)
    private String articleAuthor;
    private Date articleDate;
    @Column(length = 1000)
    private String articleType;

    @Lob
    @Column(length = 1000000) // Adjust length as needed for images
    private byte[] articleImage;


}
