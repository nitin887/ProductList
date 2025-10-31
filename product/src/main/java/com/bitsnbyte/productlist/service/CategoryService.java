package com.bitsnbyte.productlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bitsnbyte.productlist.dto.CategoryDTO;
import com.bitsnbyte.productlist.entity.Category;
import com.bitsnbyte.productlist.exception.CategoryAlreadyExistsException;
import com.bitsnbyte.productlist.mapper.CategoryMapper;
import com.bitsnbyte.productlist.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    // create category
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if (optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("category" + categoryDTO.getName() + "already exist");
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);

    }

    // get all categories

    public List<CategoryDTO> getALLCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    // get category by id

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }

    // delete category
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category " + id + "has been deleted!";
    }

    // update category
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        // Update the existing category with new data
        existingCategory.setName(categoryDTO.getName());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return CategoryMapper.toCategoryDTO(updatedCategory);
    }

}
