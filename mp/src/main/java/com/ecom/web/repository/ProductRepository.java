package com.ecom.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.web.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByName(String name);
}
