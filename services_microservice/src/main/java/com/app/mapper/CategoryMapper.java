package com.app.mapper;

import com.app.dto.CategoryRequestDTO;
import com.app.dto.CategoryResponseDTO;
import com.app.model.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryRequestDTO categoryRequestDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequestDTO, category);
        return category;
    }

    public CategoryResponseDTO toCategoryResponseDTO(Category category){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        BeanUtils.copyProperties(category, categoryResponseDTO);
        return categoryResponseDTO;
    }
}
