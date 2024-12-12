package com.wolfcode.multitenancy.repository;

import com.wolfcode.multitenancy.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenants, Long> {
}
