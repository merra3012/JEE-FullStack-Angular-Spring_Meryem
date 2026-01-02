package com.meryem.backendapp.repositories;

import com.meryem.backendapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
