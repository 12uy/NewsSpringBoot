package com.example.newssb.converter;

import com.example.newssb.dto.CategoryDTO;
import com.example.newssb.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

        public CategoryDTO toDto(CategoryEntity entity) {
            CategoryDTO result = new CategoryDTO();
            result.setId(entity.getId());
            result.setCode(entity.getCode());
            result.setName(entity.getName());
            return result;
        }

        public CategoryEntity toEntity(CategoryDTO dto) {
            CategoryEntity result = new CategoryEntity();
            result.setCode(dto.getCode());
            result.setName(dto.getName());
            return result;
        }

        public CategoryEntity toEntity(CategoryEntity result, CategoryDTO dto) {
            result.setCode(dto.getCode());
            result.setName(dto.getName());
            return result;
        }
}
