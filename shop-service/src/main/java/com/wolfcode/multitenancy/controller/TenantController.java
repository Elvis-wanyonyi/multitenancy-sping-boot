package com.wolfcode.multitenancy.controller;

import com.wolfcode.multitenancy.dto.CustomerRequest;
import com.wolfcode.multitenancy.dto.ProductRequest;
import com.wolfcode.multitenancy.dto.TenantRequest;
import com.wolfcode.multitenancy.multitenancy.TenantContext;
import com.wolfcode.multitenancy.schema.TenantSchema;
import com.wolfcode.multitenancy.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-shop")
public class TenantController {

    private final TenantSchema tenantSchema;
    private final ShopService shopService;

    public TenantController(TenantSchema tenantSchema, ShopService shopService) {
        this.tenantSchema = tenantSchema;
        this.shopService = shopService;
    }


    @GetMapping
    public String testEndpoint() {
        return "Working! 😁";
    }

    @GetMapping("/tenant")
    public String getCurrentTenant() {
        return TenantContext.getCurrentTenant();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerTenant(@RequestBody TenantRequest request) throws InterruptedException {
        tenantSchema.registerTenant(request);
        return ResponseEntity.ok("Tenant registered successfully!");
    }

    @PostMapping("/products")
    public String saveProduct(@RequestBody ProductRequest productRequest){
        shopService.saveProduct(productRequest);
        return "Product saved successfully!";
    }
    @GetMapping("products/all")
    public List<ProductRequest> allProducts(){
        return shopService.getAllProducts();
    }

    @PostMapping("/customer")
    public String addCustomer(@RequestBody CustomerRequest customerRequest){
        shopService.saveCustomer(customerRequest);
        return "Customer added successfully!";
    }
    @GetMapping("/customers/all")
    public List<CustomerRequest> allCustomers(){
        return shopService.getAllCustomers();
    }
}
