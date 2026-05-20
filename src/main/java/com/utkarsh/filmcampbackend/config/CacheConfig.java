package com.utkarsh.filmcampbackend.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager=new CaffeineCacheManager();
        cacheManager.registerCustomCache(
                "feed:collider",Caffeine.newBuilder()
                        .expireAfterWrite(60, TimeUnit.MINUTES)
                        .build()
        );

        cacheManager.registerCustomCache(
                "tmdb:trending_today",Caffeine.newBuilder()
                        .expireAfterWrite(1,TimeUnit.DAYS)
                        .build()
        );
        cacheManager.registerCustomCache(
                "tmdb:top_rated",Caffeine.newBuilder()
                        .expireAfterWrite(1,TimeUnit.DAYS)
                        .build()
        );
        return cacheManager;
    }



}
