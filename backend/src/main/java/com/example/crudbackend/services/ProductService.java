package com.example.crudbackend.services;

import com.example.crudbackend.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    Product saveProductWithFile(Product product, MultipartFile file);
}