package com.bitsnbyte.productlist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitsnbyte.productlist.dto.ProductDTO;
import com.bitsnbyte.productlist.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "product rest api crud operation", description = "create,update,read and delete operations for product rest api")
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    // getAllProduct
    @Operation(summary = "fetch all products", description = "rest api to fetch all products")

    @GetMapping
    public List<ProductDTO> getAllProduct() {
        System.out.println("GET /api/products called - getAllProduct()");
        return productService.getAllProducts();
    }

    // getProductById
    @Operation(summary = "fetch product by product id", description = "rest api to fetch product by product id")

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "create  product ", description = "rest api to create product ")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "created")

    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("POST /api/products called - createProduct()");
        System.out.println("Request body: " + productDTO);
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);

    }

    // updateProduct
    @Operation(summary = "update  product by productid ", description = "rest api to update product by productid ")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")

    @PutMapping("/{id}")

    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    // deleteProduct
    @Operation(summary = "delete product by productid ", description = "rest api to   delete product by productid ")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
