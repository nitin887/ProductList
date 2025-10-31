package com.bitsnbyte.productlist.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bitsnbyte.productlist.dto.CategoryDTO;
import com.bitsnbyte.productlist.entity.Category;
import com.bitsnbyte.productlist.exception.CategoryAlreadyExistsException;
import com.bitsnbyte.productlist.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach

    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("test");
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");
    }

    @AfterEach

    void tearDown() {

    }

    @Test
    void createCategory_categoryShouldBeCreated() {
        Mockito.when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        assertNotNull(savedCategory);
        assertEquals(categoryDTO.getName(), savedCategory.getName());
    }

    @Test
    void createCategory_categoryAlreadyExistShouldThrowException() {
        Mockito.when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.of(category));
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.createCategory(categoryDTO));
        Mockito.verify(categoryRepository, times(0)).save(Mockito.any(Category.class));
    }

    @Test

    void getAllCategories() {
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category));
        List<CategoryDTO> categories = categoryService.getALLCategories();
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(categoryDTO.getName(), categories.get(0).getName());
    }

    @Test

    void getCategoryById() {
        Mockito.when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.of(category));
        CategoryDTO foundCategory = categoryService.getCategoryById(categoryDTO.getId());
        assertNotNull(foundCategory);
        assertEquals(categoryDTO.getName(), foundCategory.getName());
    }

    @Test

    void deleteCategory() {
        categoryService.deleteCategory(categoryDTO.getId());
        Mockito.verify(categoryRepository, times(1)).deleteById(categoryDTO.getId());
    }

}
