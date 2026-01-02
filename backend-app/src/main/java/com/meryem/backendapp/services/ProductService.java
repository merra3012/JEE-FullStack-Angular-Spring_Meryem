package com.meryem.backendapp.services;

import com.meryem.backendapp.entities.Product;
import java.util.List;

public interface ProductService{
    Product addProduct(Product product);
    List<Product> findAll();
    void deleteById(Long id);
    Product updateProductById(Long id, String name,double price, long quantity );
    Product findById(Long id);
}
