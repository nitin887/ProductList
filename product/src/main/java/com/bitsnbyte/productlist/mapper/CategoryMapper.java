package com.bitsnbyte.productlist.mapper;

import com.bitsnbyte.productlist.dto.CategoryDTO;
import com.bitsnbyte.productlist.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static Category toCategoryEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
