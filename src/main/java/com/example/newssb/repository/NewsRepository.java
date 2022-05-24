package com.example.newssb.repository;

import com.example.newssb.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    List<NewsEntity> findNewsEntitiesByCategory_Id(Long id);
//    @Query("select e from news e ORDER BY RAND() LIMIT 10")
//    List<NewsEntity> findNewsEntitiesRandom();
}
