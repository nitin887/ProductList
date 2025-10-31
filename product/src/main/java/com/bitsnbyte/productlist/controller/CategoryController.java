package com.bitsnbyte.productlist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitsnbyte.productlist.dto.CategoryDTO;
import com.bitsnbyte.productlist.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "category rest api crud operation", description = "create,update,read and delete operations for category rest api")

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    // getallCategory
    @Operation(summary = "fetch all categories  ", description = "rest api to fetch  all categories along with their products")

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        System.out.println("GET /api/categories called - getAllCategories()");
        return categoryService.getALLCategories();
    }

    @Operation(summary = "create category  ", description = "rest api to create category ")
    @ApiResponse(responseCode = "201", description = "created")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // getCategoryById
    @Operation(summary = "fetch category by category id ", description = "rest api to fetch category by categoryid ")

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // delete Category
    @Operation(summary = "delete category by category id ", description = "rest api to delete category by categoryid ")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
