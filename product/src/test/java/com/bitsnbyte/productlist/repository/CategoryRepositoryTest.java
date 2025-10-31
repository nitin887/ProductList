package com.bitsnbyte.productlist.repository;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bitsnbyte.productlist.entity.Category;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach

    void setUp() {
        category = new Category();
        category.setName("test");
        categoryRepository.save(category);
    }

    @AfterEach

    void tearDown() {
        categoryRepository.delete(category);
    }

    @Test
    void findByName() {
        Category foundCategory = categoryRepository.findByName("test").orElse(null);
        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getName());
    }

    @Test
    void deleteByName() {
        categoryRepository.deleteByName("test");
        Category foundCategory = categoryRepository.findByName("test").orElse(null);
        assertNull(foundCategory);
    }
}
