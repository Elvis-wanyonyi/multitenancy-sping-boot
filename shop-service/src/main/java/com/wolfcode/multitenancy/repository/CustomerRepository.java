package com.wolfcode.multitenancy.repository;

import com.wolfcode.multitenancy.entity.ecomerce.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
}
