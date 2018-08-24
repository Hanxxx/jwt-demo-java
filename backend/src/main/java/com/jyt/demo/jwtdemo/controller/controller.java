package com.jyt.demo.jwtdemo.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jyt.demo.jwtdemo.VO.ResultVO;
import com.jyt.demo.jwtdemo.VO.articleListVO;
import com.jyt.demo.jwtdemo.data.article;
import com.jyt.demo.jwtdemo.data.articleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/forum")
public class controller {
    @Autowired
    articleRepository articleRepository;

    @PostConstruct
    public void Save() {

        articleRepository.save(new article("lh0815", "test1", "test content"));
        articleRepository.save(new article("lh0815", "test2", "test content"));

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

        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("success");
        resultVO.setReturnCode(0);
        resultVO.setData(articleListVOList);

        return resultVO;
    }


    @GetMapping("/article/detail")
    public ResultVO<article> articleDetail(@RequestParam("articleId") Integer articleId) {

        ResultVO resultVO = new ResultVO();

        if (!articleRepository.findById(articleId).isPresent()) {
            resultVO.setReturnCode(100);
            resultVO.setMsg("Can't find article by ID");
        }

        article a = articleRepository.findById(articleId).get();

        resultVO.setMsg("success");
        resultVO.setReturnCode(0);
        resultVO.setData(a);

        return resultVO;
    }

}
