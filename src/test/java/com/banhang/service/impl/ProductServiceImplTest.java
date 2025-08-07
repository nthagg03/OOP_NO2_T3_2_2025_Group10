package com.banhang.service.impl;

import com.banhang.model.dto.ProductDTO;
import com.banhang.model.entity.Product;
import com.banhang.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private ProductDTO testProductDTO;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setProductName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setImportPrice(10.0);
        testProduct.setSalePrice(15.0);
        testProduct.setStockQuantity(100);
        testProduct.setCategoryId("CAT001");
        testProduct.setUnit("pcs");
        testProduct.setIsActive(true);

        testProductDTO = new ProductDTO();
        testProductDTO.setId(1L);
        testProductDTO.setProductName("Test Product");
        testProductDTO.setDescription("Test Description");
        testProductDTO.setImportPrice(10.0);
        testProductDTO.setSalePrice(15.0);
        testProductDTO.setStockQuantity(100);
        testProductDTO.setCategoryId("CAT001");
        testProductDTO.setUnit("pcs");
        testProductDTO.setIsActive(true);
    }

    @Test
    void createProduct_ShouldReturnProductDTO_WhenValidProduct() {
        // Given
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // When
        ProductDTO result = productService.createProduct(testProductDTO);

        // Then
        assertNotNull(result);
        assertEquals(testProduct.getProductName(), result.getProductName());
        assertEquals(testProduct.getSalePrice(), result.getSalePrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getProductById_ShouldReturnProductDTO_WhenProductExists() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // When
        Optional<ProductDTO> result = productService.getProductById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testProduct.getProductName(), result.get().getProductName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductById_ShouldReturnEmpty_WhenProductNotExists() {
        // Given
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<ProductDTO> result = productService.getProductById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(999L);
    }

    @Test
    void getActiveProductsPageable_ShouldReturnPageOfProducts() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        Page<Product> productPage = new PageImpl<>(products);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(productRepository.findByIsActiveTrue(pageable)).thenReturn(productPage);

        // When
        Page<ProductDTO> result = productService.getActiveProductsPageable(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(testProduct.getProductName(), result.getContent().get(0).getProductName());
        verify(productRepository, times(1)).findByIsActiveTrue(pageable);
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProductDTO_WhenProductExists() {
        // Given
        testProductDTO.setProductName("Updated Product");
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // When
        ProductDTO result = productService.updateProduct(1L, testProductDTO);

        // Then
        assertNotNull(result);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_ShouldThrowException_WhenProductNotExists() {
        // Given
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(999L, testProductDTO);
        });
        verify(productRepository, times(1)).findById(999L);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void deleteProduct_ShouldSetProductInactive_WhenProductExists() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // When
        productService.deleteProduct(1L);

        // Then
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void isStockAvailable_ShouldReturnTrue_WhenSufficientStock() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // When
        boolean result = productService.isStockAvailable(1L, 50);

        // Then
        assertTrue(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void isStockAvailable_ShouldReturnFalse_WhenInsufficientStock() {
        // Given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // When
        boolean result = productService.isStockAvailable(1L, 150);

        // Then
        assertFalse(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void convertToDTO_ShouldConvertProductToDTO() {
        // When
        ProductDTO result = productService.convertToDTO(testProduct);

        // Then
        assertNotNull(result);
        assertEquals(testProduct.getId(), result.getId());
        assertEquals(testProduct.getProductName(), result.getProductName());
        assertEquals(testProduct.getSalePrice(), result.getSalePrice());
        assertEquals(testProduct.getStockQuantity(), result.getStockQuantity());
    }

    @Test
    void convertToEntity_ShouldConvertDTOToProduct() {
        // When
        Product result = productService.convertToEntity(testProductDTO);

        // Then
        assertNotNull(result);
        assertEquals(testProductDTO.getId(), result.getId());
        assertEquals(testProductDTO.getProductName(), result.getProductName());
        assertEquals(testProductDTO.getSalePrice(), result.getSalePrice());
        assertEquals(testProductDTO.getStockQuantity(), result.getStockQuantity());
    }
}