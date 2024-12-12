package com.wolfcode.multitenancy.service;

import com.wolfcode.multitenancy.dto.CustomerRequest;
import com.wolfcode.multitenancy.dto.ProductRequest;
import com.wolfcode.multitenancy.entity.ecomerce.Customers;
import com.wolfcode.multitenancy.entity.ecomerce.Products;
import com.wolfcode.multitenancy.repository.CustomerRepository;
import com.wolfcode.multitenancy.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public ShopService(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public void saveProduct(ProductRequest productRequest) {
        Products product = Products.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductRequest> getAllProducts() {
        List<Products> products = productRepository.findAll();

        return products.stream().map(this::mapToResponse).toList();
    }

    private ProductRequest mapToResponse(Products product) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(product.getName());
        productRequest.setPrice(product.getPrice());

        return productRequest;
    }

    public void saveCustomer(CustomerRequest customerRequest) {
        Customers customer = Customers.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .build();
        customerRepository.save(customer);
    }

    public List<CustomerRequest> getAllCustomers() {
        List<Customers> customers = customerRepository.findAll();
        return customers.stream().map(this::mapToCustomer).toList();
    }

    private CustomerRequest mapToCustomer(Customers customer) {
        CustomerRequest customers = new CustomerRequest();
        customers.setName(customer.getName());
        customers.setEmail(customer.getEmail());
        customers.setPhone(customer.getPhone());

        return customers;
    }

}
