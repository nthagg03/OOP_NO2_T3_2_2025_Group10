package com.banhang.service.impl;

import com.banhang.model.dto.ProductDTO;
import com.banhang.model.entity.Product;
import com.banhang.repository.ProductRepository;
import com.banhang.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product.setCreatedDate(LocalDateTime.now());
        product.setIsActive(true);
        
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        // Update fields
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setImportPrice(productDTO.getImportPrice());
        existingProduct.setSalePrice(productDTO.getSalePrice());
        existingProduct.setStockQuantity(productDTO.getStockQuantity());
        existingProduct.setCategoryId(productDTO.getCategoryId());
        existingProduct.setBarcode(productDTO.getBarcode());
        existingProduct.setUnit(productDTO.getUnit());
        existingProduct.setUpdatedDate(LocalDateTime.now());
        
        Product savedProduct = productRepository.save(existingProduct);
        return convertToDTO(savedProduct);
    }
    
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        // Soft delete by setting isActive to false
        product.setIsActive(false);
        product.setUpdatedDate(LocalDateTime.now());
        productRepository.save(product);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> getAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getActiveProducts() {
        return productRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> getActiveProductsPageable(Pageable pageable) {
        return productRepository.findByIsActiveTrue(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> searchProductsByName(String name, Pageable pageable) {
        return productRepository.findByProductNameContainingIgnoreCaseAndIsActiveTrue(name, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByCategory(String categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> getProductsByCategory(String categoryId, Pageable pageable) {
        return productRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProductByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> searchProducts(String name, String categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.searchProducts(name, categoryId, minPrice, maxPrice, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getLowStockProducts(Integer threshold) {
        return productRepository.findLowStockProducts(threshold)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean updateStock(Long productId, Integer quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setStockQuantity(quantity);
            product.setUpdatedDate(LocalDateTime.now());
            productRepository.save(product);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isStockAvailable(Long productId, Integer requiredQuantity) {
        return productRepository.findById(productId)
                .map(product -> product.getStockQuantity() >= requiredQuantity)
                .orElse(false);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getTotalProductCount() {
        return productRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getActiveProductCount() {
        return productRepository.countByIsActiveTrue();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getProductCountByCategory(String categoryId) {
        return productRepository.countByCategoryIdAndIsActiveTrue(categoryId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Double getTotalStockValue() {
        Double value = productRepository.getTotalStockValue();
        return value != null ? value : 0.0;
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode).isPresent();
    }
    
    @Override
    public void activateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setIsActive(true);
        product.setUpdatedDate(LocalDateTime.now());
        productRepository.save(product);
    }
    
    @Override
    public void deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setIsActive(false);
        product.setUpdatedDate(LocalDateTime.now());
        productRepository.save(product);
    }
    
    @Override
    public ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setImportPrice(product.getImportPrice());
        dto.setSalePrice(product.getSalePrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategoryId(product.getCategoryId());
        dto.setBarcode(product.getBarcode());
        dto.setUnit(product.getUnit());
        dto.setIsActive(product.getIsActive());
        
        // Calculate computed fields
        dto.setProfitMargin(product.getProfitMargin());
        dto.setLowStock(product.isLowStock(10)); // Threshold of 10
        
        return dto;
    }
    
    @Override
    public Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setImportPrice(dto.getImportPrice());
        product.setSalePrice(dto.getSalePrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategoryId(dto.getCategoryId());
        product.setBarcode(dto.getBarcode());
        product.setUnit(dto.getUnit());
        product.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        
        return product;
    }
}