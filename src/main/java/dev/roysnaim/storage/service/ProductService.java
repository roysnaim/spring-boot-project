package dev.roysnaim.storage.service;

import ch.qos.logback.core.CoreConstants;
import dev.roysnaim.storage.model.Product;
import dev.roysnaim.storage.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if(existsProduct(id)) {
            product.setId(id);
            product.setCreationDate(productRepository.findById(id).get().getCreationDate());
        }
        return productRepository.save(product);
    }

    public boolean existsProduct(Long id) {
        return productRepository.existsById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
