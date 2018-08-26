package com.jyt.demo.jwtdemo.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jyt.demo.jwtdemo.Package.ResultVOUtil;
import com.jyt.demo.jwtdemo.VO.ResultVO;
import com.jyt.demo.jwtdemo.VO.articleListVO;
import com.jyt.demo.jwtdemo.data.article;
import com.jyt.demo.jwtdemo.data.articleForm;
import com.jyt.demo.jwtdemo.data.articleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/forum")
@Slf4j
public class controller {
    @Autowired
    private articleRepository articleRepository;

    private GsonBuilder gsonBuilder;

    private Gson gson;

    @PostConstruct
    public void Save() {

        articleRepository.save(new article("lh0815", "test1", "test content"));
        articleRepository.save(new article("lh0815", "test2", "test content"));

        this.gsonBuilder = new GsonBuilder();
        this.gsonBuilder.setPrettyPrinting();
        this.gson = gsonBuilder.create();

    }

    /**
     * No need for authorization. Anyone can view the article list.
     * @return resultVO.
     */
    @GetMapping("/article/list")
    public ResultVO<List<articleListVO>> articleList() {

        List<article> articleList = articleRepository.findAll();

        List<articleListVO> articleListVOList = articleList.stream().map(
                e -> new articleListVO(e)
        ).collect(Collectors.toList());

        return ResultVOUtil.success(articleListVOList);
    }


    @GetMapping("/article/detail")
    public ResultVO<article> articleDetail(@RequestParam("articleId") Integer articleId) {


        if (!articleRepository.findById(articleId).isPresent()) {
            return ResultVOUtil.error(100, "INPUT FORMAT ERROR");
        }

        article a = articleRepository.findById(articleId).get();

        return ResultVOUtil.success(a);
    }

    @PostMapping("/article/create")
    public ResultVO create(@RequestBody String Body) {


        articleForm newArticleForm;
        try {
            newArticleForm = this.gson.fromJson(Body, articleForm.class);
        } catch (Exception e) {
            log.error("From json to string error. E = {}", e.toString());
            return ResultVOUtil.error(100, "INPUT FORMAT ERROR");
        }
        article newArticle = new article();
        //log.info("Request Body: {}", newArticleForm.getContent());
        BeanUtils.copyProperties(newArticleForm, newArticle);
        articleRepository.save(newArticle);
        return ResultVOUtil.success();
    }

    @PutMapping("/article/edit")
    public ResultVO edit(@RequestParam("articleId") Integer articleId,
                         @RequestBody String body) {

        if (!articleRepository.findById(articleId).isPresent()) {
            return ResultVOUtil.error(101, "Can't find article by ID");
        }

        articleForm newArticleForm;
        try {
            newArticleForm = this.gson.fromJson(body, articleForm.class);
        } catch (Exception e) {
            log.error("From json to string error. E = {}", e.toString());
            return ResultVOUtil.error(100, "INPUT FORMAT ERROR");
        }

        article article = articleRepository.findById(articleId).get();

        article.setContent(newArticleForm.getContent());

        article.setTitle(newArticleForm.getTitle());

        return ResultVOUtil.success();

    }


}
