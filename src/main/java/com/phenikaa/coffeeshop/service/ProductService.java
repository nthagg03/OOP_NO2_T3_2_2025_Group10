package com.phenikaa.coffeeshop.service;

import com.phenikaa.coffeeshop.model.Product;
import com.phenikaa.coffeeshop.repository.ProductRepository;
import com.phenikaa.coffeeshop.exception.ResourceNotFoundException;
import com.phenikaa.coffeeshop.exception.DuplicateResourceException;
import com.phenikaa.coffeeshop.exception.InsufficientStockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Product Service - Business logic layer for Product operations
 * Provides CRUD operations and business rules for products
 */
@Service
@Transactional
public class ProductService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    // CREATE operations
    public Product createProduct(Product product) {
        logger.info("Creating new product: {}", product.getName());
        
        // Check if product ID already exists
        if (productRepository.findByProductId(product.getProductId()).isPresent()) {
            throw new DuplicateResourceException("Product with ID " + product.getProductId() + " already exists");
        }
        
        // Validate business rules
        validateProduct(product);
        
        Product savedProduct = productRepository.save(product);
        logger.info("Product created successfully with ID: {}", savedProduct.getId());
        return savedProduct;
    }
    
    // READ operations
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        logger.debug("Fetching all products");
        return productRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Product> getAllActiveProducts() {
        logger.debug("Fetching all active products");
        return productRepository.findByActiveTrue();
    }
    
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        logger.debug("Fetching product by ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public Product getProductByProductId(String productId) {
        logger.debug("Fetching product by product ID: {}", productId);
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
    }
    
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        logger.debug("Fetching products by category: {}", category);
        return productRepository.findByCategoryAndActiveTrue(category);
    }
    
    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        logger.debug("Searching products by name: {}", name);
        return productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name);
    }
    
    @Transactional(readOnly = true)
    public List<Product> getLowStockProducts(Integer threshold) {
        logger.debug("Fetching low stock products with threshold: {}", threshold);
        return productRepository.findLowStockProducts(threshold);
    }
    
    @Transactional(readOnly = true)
    public List<Product> getInStockProducts() {
        logger.debug("Fetching in-stock products");
        return productRepository.findInStockProducts();
    }
    
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        logger.debug("Fetching products in price range: {} - {}", minPrice, maxPrice);
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }
    
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        logger.debug("Fetching all product categories");
        return productRepository.findDistinctCategories();
    }
    
    // UPDATE operations
    public Product updateProduct(Long id, Product productDetails) {
        logger.info("Updating product with ID: {}", id);
        
        Product existingProduct = getProductById(id);
        
        // Check if changing product ID would create duplicate
        if (!existingProduct.getProductId().equals(productDetails.getProductId()) &&
            productRepository.findByProductId(productDetails.getProductId()).isPresent()) {
            throw new DuplicateResourceException("Product with ID " + productDetails.getProductId() + " already exists");
        }
        
        // Update fields
        existingProduct.setProductId(productDetails.getProductId());
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setCostPrice(productDetails.getCostPrice());
        existingProduct.setStock(productDetails.getStock());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setActive(productDetails.getActive());
        
        validateProduct(existingProduct);
        
        Product updatedProduct = productRepository.save(existingProduct);
        logger.info("Product updated successfully: {}", updatedProduct.getId());
        return updatedProduct;
    }
    
    public Product updateStock(String productId, Integer newStock) {
        logger.info("Updating stock for product ID: {} to {}", productId, newStock);
        
        Product product = getProductByProductId(productId);
        product.setStock(newStock);
        
        Product updatedProduct = productRepository.save(product);
        logger.info("Stock updated successfully for product: {}", productId);
        return updatedProduct;
    }
    
    public Product adjustStock(String productId, Integer adjustment) {
        logger.info("Adjusting stock for product ID: {} by {}", productId, adjustment);
        
        Product product = getProductByProductId(productId);
        int newStock = product.getStock() + adjustment;
        
        if (newStock < 0) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId + 
                ". Current stock: " + product.getStock() + ", requested adjustment: " + adjustment);
        }
        
        product.setStock(newStock);
        
        Product updatedProduct = productRepository.save(product);
        logger.info("Stock adjusted successfully for product: {}", productId);
        return updatedProduct;
    }
    
    public Product deactivateProduct(Long id) {
        logger.info("Deactivating product with ID: {}", id);
        
        Product product = getProductById(id);
        product.setActive(false);
        
        Product updatedProduct = productRepository.save(product);
        logger.info("Product deactivated successfully: {}", id);
        return updatedProduct;
    }
    
    public Product reactivateProduct(Long id) {
        logger.info("Reactivating product with ID: {}", id);
        
        Product product = getProductById(id);
        product.setActive(true);
        
        Product updatedProduct = productRepository.save(product);
        logger.info("Product reactivated successfully: {}", id);
        return updatedProduct;
    }
    
    // DELETE operations
    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID: {}", id);
        
        Product product = getProductById(id);
        productRepository.delete(product);
        
        logger.info("Product deleted successfully: {}", id);
    }
    
    // Business logic methods
    public boolean isInStock(String productId, Integer quantity) {
        Product product = getProductByProductId(productId);
        return product.isInStock(quantity);
    }
    
    public void reserveStock(String productId, Integer quantity) {
        logger.info("Reserving {} units of product: {}", quantity, productId);
        
        Product product = getProductByProductId(productId);
        
        if (!product.isInStock(quantity)) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId + 
                ". Requested: " + quantity + ", Available: " + product.getStock());
        }
        
        product.updateStock(-quantity);
        productRepository.save(product);
        
        logger.info("Stock reserved successfully: {} units of {}", quantity, productId);
    }
    
    public void releaseStock(String productId, Integer quantity) {
        logger.info("Releasing {} units of product: {}", quantity, productId);
        
        Product product = getProductByProductId(productId);
        product.updateStock(quantity);
        productRepository.save(product);
        
        logger.info("Stock released successfully: {} units of {}", quantity, productId);
    }
    
    @Transactional(readOnly = true)
    public Long getProductCountByCategory(String category) {
        return productRepository.countByCategory(category);
    }
    
    @Transactional(readOnly = true)
    public List<Product> getProductsByMinimumProfit(Double minProfit) {
        return productRepository.findByMinimumProfit(minProfit);
    }
    
    // Validation helper
    private void validateProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        
        if (product.getCostPrice() <= 0) {
            throw new IllegalArgumentException("Product cost price must be greater than 0");
        }
        
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        
        if (product.getPrice() <= product.getCostPrice()) {
            logger.warn("Product {} has price ({}) lower than or equal to cost price ({})", 
                product.getName(), product.getPrice(), product.getCostPrice());
        }
    }
}