package com.utkarsh.filmcampbackend.service;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.utkarsh.filmcampbackend.dto.ColliderArticleDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.StringReader;
import java.util.List;

@Service
public class FeedService {
    private final RestClient restClient;

    public FeedService(@Qualifier("rssRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Cacheable("feed:slashfilm")
    public List<ColliderArticleDTO> getSlashFilmFeed(){
        try {
            String rss=restClient.get()
                    .uri("https://www.slashfilm.com/feed/")
                    .retrieve()
                    .body(String.class);
            SyndFeedInput input=new SyndFeedInput();
            SyndFeed feed=input.build(new StringReader(rss));
            return feed.getEntries()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }catch (Exception e){
            throw new RuntimeException("rss feed exception",e);
        }
    }
    public ColliderArticleDTO mapToDTO(SyndEntry entry){
        ColliderArticleDTO dto=new ColliderArticleDTO();
        dto.setTitle(entry.getTitle());
        dto.setLink(entry.getLink());
        dto.setAuthor(entry.getAuthor());
        dto.setDescription(entry.getDescription().getValue());
        if(!entry.getEnclosures().isEmpty())
            dto.setImageUrl(entry
                    .getEnclosures()
                    .getFirst()
                    .getUrl());
        if(entry.getCategories()!=null){
            dto.setCategories(
                    entry.getCategories()
                            .stream()
                            .map(SyndCategory::getName)
                            .toList()
            );
        }
        if(entry.getPublishedDate()!=null){
            dto.setPublishedDate(
                    entry.getPublishedDate().toInstant()
            );
        }
        return dto;
    }
}
