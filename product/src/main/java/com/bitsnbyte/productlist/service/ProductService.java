package com.bitsnbyte.productlist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bitsnbyte.productlist.dto.ProductDTO;
import com.bitsnbyte.productlist.entity.Category;
import com.bitsnbyte.productlist.entity.Product;
import com.bitsnbyte.productlist.exception.CategoryNotFoundException;
import com.bitsnbyte.productlist.mapper.ProductMapper;
import com.bitsnbyte.productlist.repository.CategoryRepository;
import com.bitsnbyte.productlist.repository.ProductRepository;

@Service

public class ProductService {

  private ProductRepository productRepository;
  private CategoryRepository categoryRepository;

  public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  public ProductDTO createProduct(ProductDTO productDTO) {
    System.out.println("ProductService.createProduct() called with: " + productDTO);
    System.out.println("CategoryId: " + productDTO.getCategoryId());

    /**
     * id,name,description,price,categoryId
     */
    //
    Category category = categoryRepository
        .findById(productDTO.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("category" +
            productDTO.getCategoryId() + " not found!"));

    // DTO-> entity
    Product product = ProductMapper.toProductEntity(productDTO, category);
    product = productRepository.save(product);
    // ENTITY->DTO
    return ProductMapper.toProductDTO(product);
  }

  // get all products
  public List<ProductDTO> getAllProducts() {
    try {
      List<Product> allProducts = productRepository.findAll();
      System.out.println("Found " + allProducts.size() + " products in database");

      List<ProductDTO> validProducts = new ArrayList<>();

      for (Product product : allProducts) {
        try {
          if (product.getCategory() == null) {
            System.out.println("Product " + product.getId() + " has null category - skipping");
            continue;
          }

          Long categoryId = product.getCategory().getId();
          if (categoryId == null) {
            System.out.println("Product " + product.getId() + " has category with null ID - skipping");
            continue;
          }

          // Verify category exists
          if (!categoryRepository.existsById(categoryId)) {
            System.out.println(
                "Product " + product.getId() + " references non-existent category " + categoryId + " - skipping");
            continue;
          }

          ProductDTO productDTO = ProductMapper.toProductDTO(product);
          validProducts.add(productDTO);

        } catch (Exception e) {
          System.out.println("Error processing product " + product.getId() + ": " + e.getMessage());
          // Continue with other products instead of failing completely
        }
      }

      System.out.println("Returning " + validProducts.size() + " valid products");
      return validProducts;

    } catch (Exception e) {
      System.out.println("Error in getAllProducts: " + e.getMessage());
      e.printStackTrace(System.err);
      throw e;
    }
  }

  // get product by id
  public ProductDTO getProductById(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    return ProductMapper.toProductDTO(product);
  }

  // update product
  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    Category category = categoryRepository.findById(productDTO.getCategoryId())
        .orElseThrow(() -> new RuntimeException("category not found!"));
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    product.setCategory(category);
    productRepository.save(product);
    return ProductMapper.toProductDTO(product);

  }

  // delete product
  public String deleteProduct(Long id) {
    productRepository.deleteById(id);
    return "product" + id + "has been deleted!";
  }

}
