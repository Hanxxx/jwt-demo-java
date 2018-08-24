package com.jyt.demo.jwtdemo.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class articleRepositoryTest {

    @Autowired
    articleRepository repository;

    @Test
    public void saveOne() {
        article a = new article("lh0815", "test", "test content");

        article res = repository.save(a);

        Assert.assertNotEquals(res, null);

        List<article> li = repository.findAll();

        Assert.assertNotEquals(li.size(), 0);
    }

}