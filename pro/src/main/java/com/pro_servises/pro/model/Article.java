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
    private Integer article_id;
    private String article_title;
    private String article_content;
    private String article_author;
    private Date article_date;
    private String article_type;
    private String article_image;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
