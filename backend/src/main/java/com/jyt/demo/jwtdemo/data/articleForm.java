package com.jyt.demo.jwtdemo.data;

import lombok.Data;

@Data
public class articleForm {

    private String authorId;

    private String title;

    private String content;

    public articleForm() {}
}
