package com.jyt.demo.jwtdemo.data;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Article class for demo only.
 * Login user can read any articles. Only authors can edit their article.
 * Forum Admin has the right to edit any article.
 */
@Data
@Entity
public class article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    private String authorId;

    private String title;

    private String content;

    public article() {
    }

    public article(String authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }
}
