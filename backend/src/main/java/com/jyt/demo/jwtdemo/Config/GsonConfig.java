package com.jyt.demo.jwtdemo.Config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;
import org.springframework.stereotype.Component;

@Component
public class GsonConfig {
    @Bean
    public Gson GsonBean() {

        Gson gson = GsonBuilderBean().create();

        return gson;
    }

    @Bean
    public GsonBuilder GsonBuilderBean() {
        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder;
    }
}
