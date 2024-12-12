package com.wolfcode.multitenancy.repository;

import com.wolfcode.multitenancy.entity.ecomerce.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
}
