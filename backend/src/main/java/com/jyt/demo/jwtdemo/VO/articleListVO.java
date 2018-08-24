package com.jyt.demo.jwtdemo.VO;

import com.jyt.demo.jwtdemo.data.article;
import lombok.Data;

@Data
public class articleListVO {


    private Integer articleId;

    private String authorId;

    private String title;

    public articleListVO(article article) {

        this.articleId = article.getArticleId();

        this.authorId = article.getAuthorId();

        this.title = article.getTitle();

    }

}
