package com.wolfcode.multitenancy.schema;

import com.wolfcode.multitenancy.dto.TenantRequest;
import com.wolfcode.multitenancy.entity.Tenants;
import com.wolfcode.multitenancy.repository.TenantRepository;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TenantSchema {

    @Value("${spring.flyway.locations}")
    private String migrationPath;

    @Value("${spring.datasource.url}")
    private String jdbcUrlTemplate;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final TenantRepository tenantRepository;

    public TenantSchema(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;

    }


    public void registerTenant(TenantRequest request) {

        try {

            Flyway flyway = Flyway.configure()
                    .dataSource(jdbcUrlTemplate, username, password)
                    .schemas(request.getName())
                    .locations(migrationPath)
                    .load();
            flyway.migrate();

            Tenants tenants = Tenants.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .build();
            tenantRepository.save(tenants);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
