package com.app.service;

import com.app.dto.CategoryRequestDTO;
import com.app.dto.CategoryResponseDTO;
import com.app.mapper.CategoryMapper;
import com.app.model.Category;
import com.app.repository.CategoryRepository;
import com.app.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private ServiceRepository serviceRepository;
    private CategoryMapper categoryMapper;

    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO){
        Category category = categoryMapper.toCategory(categoryRequestDTO);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDTO(category);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO){
        if(!categoryRepository.existsById(id))
            throw new RuntimeException("Category " +id+ " not found");
        Category category = categoryMapper.toCategory(categoryRequestDTO);
        category.setId(id);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDTO(category);
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category " +id+ " not found"));
        // Check if category has services
        if(serviceRepository.existsByCategoryId(id))
            throw new RuntimeException("Could not delete category " + id + ", has services");
        categoryRepository.delete(category);
    }

    public CategoryResponseDTO getCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category " +id+ " not found"));
        return categoryMapper.toCategoryResponseDTO(category);
    }

    public List<CategoryResponseDTO> getCategories(){
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.toCategoryResponseDTO(category))
                .collect(Collectors.toList());
    }
}
