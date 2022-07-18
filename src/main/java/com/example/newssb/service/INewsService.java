package com.example.newssb.service;

import com.example.newssb.dto.NewsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewsService {
    List<NewsDTO> findAll(Pageable pageable);
    int getTotalItem();
    NewsDTO findById(Long id);

    NewsDTO save(NewsDTO dto);
    void delete(Long[] ids);



    List<NewsDTO> findByCategoryId(Long id);

//    List<NewsDTO> findNewsEntitiesRandom();


}
