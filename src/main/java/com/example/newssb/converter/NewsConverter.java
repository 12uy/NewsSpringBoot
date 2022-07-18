package com.example.newssb.converter;

import com.example.newssb.dto.NewsDTO;
import com.example.newssb.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {
    public static NewsDTO toDTO(NewsEntity newsEntity) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(newsEntity.getId());
        newsDTO.setTitle(newsEntity.getTitle());
        newsDTO.setContent(newsEntity.getContent());
        newsDTO.setCategoryCode(newsEntity.getCategory().getCode());
        newsDTO.setShortdescription(newsEntity.getShortDescription());
        newsDTO.setLinkTTS(newsEntity.getLinkTTS());
        newsDTO.setHtmlPage(newsEntity.getHtmlPage());
        newsDTO.setTomTat(newsEntity.getTomTat());
        return newsDTO;
    }

    public static NewsEntity toEntity(NewsDTO newsDTO) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setId(newsDTO.getId());
        newsEntity.setTitle(newsDTO.getTitle());
        newsEntity.setContent(newsDTO.getContent());
//        newsEntity.setCategory(newsDTO.getCategory());
        newsEntity.setShortDescription(newsDTO.getShortdescription());
        newsEntity.setLinkTTS(newsDTO.getLinkTTS());
        newsEntity.setTomTat(newsDTO.getTomTat());
        return newsEntity;
    }

    public static NewsEntity toEntity(NewsEntity newsEntity, NewsDTO newsDTO) {
        newsEntity.setTitle(newsDTO.getTitle());
        newsEntity.setContent(newsDTO.getContent());
//        newsEntity.setCategory(newsDTO.getCategory());
        newsEntity.setShortDescription(newsDTO.getShortdescription());
        newsEntity.setLinkTTS(newsDTO.getLinkTTS());
        return newsEntity;
    }
}
