package com.example.crudbackend.controllers;

import com.example.crudbackend.config.FileUploadUtil;
import com.example.crudbackend.entities.Product;
import com.example.crudbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("newProduct", new Product());
        return "products";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("newProduct") Product product, @RequestParam("file") MultipartFile file) {
        String uploadDir = "src/main/resources/static/productsImages";
        try {
            String fileName = FileUploadUtil.saveFile(uploadDir, file);
            product.setProductImagePath(fileName);
            productService.saveProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        Product existingProduct = productService.getProductById(id);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductCategory(product.getProductCategory());
        existingProduct.setProductStock(product.getProductStock());
        existingProduct.setProductPrice(product.getProductPrice());
        productService.updateProduct(existingProduct);
        return "redirect:/products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
