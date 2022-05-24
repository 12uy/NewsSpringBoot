package com.example.newssb.service.impl;

import com.example.newssb.converter.NewsConverter;
import com.example.newssb.dto.NewsDTO;
import com.example.newssb.entity.CategoryEntity;
import com.example.newssb.entity.NewsEntity;
import com.example.newssb.repository.CategoryRepository;
import com.example.newssb.repository.NewsRepository;
import com.example.newssb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public List<NewsDTO> findAll(Pageable pageable) {
        List<NewsDTO> result = new ArrayList<>();
        List<NewsEntity> entities = newsRepository.findAll(pageable).getContent();
        for (NewsEntity item: entities) {
            NewsDTO newsDTO = newsConverter.toDTO(item);
            result.add(newsDTO);
        }
        return result;
    }


    @Override
    public int getTotalItem() {
        return (int) newsRepository.count();
    }

    @Override
    public NewsDTO findById(Long id) {
        Optional<NewsEntity> entity = newsRepository.findById(id);
        return newsConverter.toDTO(entity.get());
    }


    @Override
    @Transactional
    public NewsDTO save(NewsDTO dto) {
        CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
        NewsEntity newsEntity = new NewsEntity();
        if (dto.getId() != null){
            NewsEntity oldNews = newsRepository.findById(dto.getId()).get();
            oldNews.setCategory(category);
            newsEntity = newsConverter.toEntity(oldNews, dto);
        } else {
            newsEntity = newsConverter.toEntity(dto);
            newsEntity.setCategory(category);
        }
        return newsConverter.toDTO(newsRepository.save(newsEntity));
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id: ids) {
            newsRepository.deleteById(id);
        }
    }

    @Override
    public List<NewsDTO> findByCategoryId(Long id) {
        List<NewsDTO> result = new ArrayList<>();
        List<NewsEntity> entities = newsRepository.findNewsEntitiesByCategory_Id(id);
        for (NewsEntity item: entities) {
            NewsDTO newsDTO = newsConverter.toDTO(item);
            result.add(newsDTO);
        }
        return result;
    }

//    @Override
//    public List<NewsDTO> findNewsEntitiesRandom() {
//        List<NewsDTO> result = new ArrayList<>();
//        List<NewsEntity> entities = newsRepository.findNewsEntitiesRandom();
//        for (NewsEntity item: entities) {
//            NewsDTO newsDTO = newsConverter.toDTO(item);
//            result.add(newsDTO);
//        }
//        return result;
//    }


}
