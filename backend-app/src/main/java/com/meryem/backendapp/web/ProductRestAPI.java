// This is for the REST API controller that handles HTTP requests related to Product entities.
package com.meryem.backendapp.web;

import com.meryem.backendapp.entities.Product;
import com.meryem.backendapp.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*") // To allow requests from any origin (useful for frontend-backend communication)
@RequestMapping("/api") // To avoid conflict with thymeleaf and define a common prefix for all endpoints.
@AllArgsConstructor
public class ProductRestAPI {
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(name = "id")Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable(name = "id") Long id){
        productService.deleteById(id);
    }

}
