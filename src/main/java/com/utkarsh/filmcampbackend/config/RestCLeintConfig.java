package com.utkarsh.filmcampbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestCLeintConfig {
    @Value("${tmdb.api-key}")
    String tmdbKey;
    @Bean
    public RestClient tmdbRestClient(){
        return RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("accept","application/json")
                .defaultHeader("Authorization","Bearer "+tmdbKey)
                .build();
    }

    @Bean
    public RestClient rssRestClient(){
        return RestClient.builder()
                .defaultHeader("accept","application/rss+xml")
                .build();
    }

}
