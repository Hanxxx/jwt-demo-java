package com.jyt.demo.jwtdemo.VO;


import lombok.Data;

@Data
public class ResultVO<T> {

    private Integer returnCode;

    private String msg;

    private T data;

}
