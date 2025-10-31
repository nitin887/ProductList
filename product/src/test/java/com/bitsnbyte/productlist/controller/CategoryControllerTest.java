
package com.bitsnbyte.productlist.controller;

import static org.mockito.Mockito.when;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import com.bitsnbyte.productlist.config.SecurityTestConfig;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bitsnbyte.productlist.controller.CategoryController;
import com.bitsnbyte.productlist.dto.CategoryDTO;
import com.bitsnbyte.productlist.service.CategoryService;

@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@ActiveProfiles("test")
public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categorycontroller;
    @Mock
    private CategoryService categoryservice;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categorycontroller).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_getAllCategories() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");
        List<CategoryDTO> categories = List.of(categoryDTO);
        when(categoryservice.getALLCategories()).thenReturn(categories);
        mockMvc.perform(get("/api/categories")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(categoryDTO.getName()));
    }

}
