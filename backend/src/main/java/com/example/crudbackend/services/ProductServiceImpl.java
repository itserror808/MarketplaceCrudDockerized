package com.example.crudbackend.services;

import com.example.crudbackend.entities.Product;
import com.example.crudbackend.exceptions.FileStorageException;
import com.example.crudbackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product saveProductWithFile(Product product, MultipartFile file) throws FileStorageException {
        String filePath = saveFile(file);
        product.setProductImagePath(filePath);
        return productRepository.save(product);
    }

    private String saveFile(MultipartFile file) throws FileStorageException {
        try {
            // Define the upload directory
            String uploadDir = "assets/media/productImages";
            Path uploadPath = Paths.get(uploadDir + file.getOriginalFilename());

            // Save the file
            Files.copy(file.getInputStream(), uploadPath);

            // Return the file path
            return uploadPath.toString();
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", e);
        }
    }

}